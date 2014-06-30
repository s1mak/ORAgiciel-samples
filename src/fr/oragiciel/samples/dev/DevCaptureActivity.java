package fr.oragiciel.samples.dev;

import java.util.ArrayList;
import java.util.List;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import fr.oragiciel.samples.R;

public class DevCaptureActivity extends AbstractActivity {

	private boolean previewing;
	private TextView devCaptureText;
	private List<String> infos;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_capture);
		previewing = false;
		devCaptureText = (TextView) findViewById(R.id.devCaptureText);
		getCameraManager().setUserSurfaceView(
				(SurfaceView) findViewById(R.id.surfaceView1));

		Camera.Parameters params = getCameraManager().getCameraParameters();
		StringBuilder builder;
		infos = new ArrayList<String>();
		index = 0;

		builder = new StringBuilder("Video Size : ");
		for (Size size : params.getSupportedVideoSizes()) {
			builder.append(size.width).append("x").append(size.height)
					.append(", ");
		}
		infos.add(builder.toString());

		builder = new StringBuilder("Preview Size : ");
		for (Size size : params.getSupportedPreviewSizes()) {
			builder.append(size.width).append("x").append(size.height)
					.append(", ");
		}
		infos.add(builder.toString());

		builder = new StringBuilder("Picture Size : ");
		for (Size size : params.getSupportedPictureSizes()) {
			builder.append(size.width).append("x").append(size.height)
					.append(", ");
		}
		infos.add(builder.toString());

		builder = new StringBuilder("Focus Mode : ");
		for (String focus : params.getSupportedFocusModes()) {
			builder.append(focus).append(", ");
		}
		infos.add(builder.toString());

		builder = new StringBuilder("FPS : ");
		for (int[] fps : params.getSupportedPreviewFpsRange()) {
			builder.append(fps[0]).append("<->").append(fps[1]).append("\n");
		}
		infos.add(builder.toString());

		builder = new StringBuilder("Picture Format (BPP) : ");
		for (Integer format : params.getSupportedPictureFormats()) {
			switch (format) {
			case ImageFormat.UNKNOWN:
				builder.append("UNKNOWN");
				break;
			case ImageFormat.JPEG:
				builder.append("JPEG");
				break;
			case ImageFormat.NV21:
				builder.append("NV21");
				break;
			case ImageFormat.RGB_565:
				builder.append("RGB_565");
				break;
			case ImageFormat.YUY2:
				builder.append("YUY2");
				break;
			case ImageFormat.YV12:
				builder.append("YV12");
				break;
			default:
				builder.append("default");
				break;
			}
			builder.append(" (").append(ImageFormat.getBitsPerPixel(format)).append("), ");
		}
		infos.add(builder.toString());

		builder = new StringBuilder("Preview Format (BPP) : ");
		for (Integer format : params.getSupportedPreviewFormats()) {
			switch (format) {
			case ImageFormat.UNKNOWN:
				builder.append("UNKNOWN");
				break;
			case ImageFormat.JPEG:
				builder.append("JPEG");
				break;
			case ImageFormat.NV21:
				builder.append("NV21");
				break;
			case ImageFormat.RGB_565:
				builder.append("RGB_565");
				break;
			case ImageFormat.YUY2:
				builder.append("YUY2");
				break;
			case ImageFormat.YV12:
				builder.append("YV12");
				break;
			default:
				builder.append("default");
				break;
			}
			builder.append(" (").append(ImageFormat.getBitsPerPixel(format)).append("), ");
		}
		infos.add(builder.toString());
		
		devCaptureText.setText(infos.get(index));
	}

	@Override
	protected void onPause() {
		super.onPause();
		getCameraManager().stopCamera();
	}

	@Override
	public void onTouch() {
		if (!previewing) {
			devCaptureText.setVisibility(View.GONE);
			SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
			surfaceView.setBackgroundResource(0);
			getCameraManager().startCamera();
			previewing = true;
		} else {
			getCameraManager().stopCamera();
			previewing = false;
			devCaptureText.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onMoveForward() {
		super.onMoveForward();
		index++;
		if (index >= infos.size()) {
			index = 0;
		}
		devCaptureText.setText(infos.get(index));
	}

	@Override
	public void onMoveBackward() {
		super.onMoveBackward();
		index--;
		if (index < 0) {
			index = infos.size() - 1;
		}
		devCaptureText.setText(infos.get(index));
	}
}
