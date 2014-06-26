package fr.oragiciel.samples.dev;

import java.io.IOException;

import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import fr.oragiciel.samples.R;
import fr.oragiciel.sdk.camera.FrameListener;

public class DevCaptureActivity extends AbstractActivity implements FrameListener {

	private boolean previewing;
	private TextView devCaptureText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_capture);
		previewing = false;
		devCaptureText = (TextView) findViewById(R.id.devCaptureText);
		getCameraManager().setUserSurfaceView((SurfaceView) findViewById(R.id.surfaceView1));
		getCameraManager().addFullFrameListeners(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		getCameraManager().stopCamera();
	}

	@Override
	public void onTouch() {
		if (!previewing) {
			SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
			surfaceView.setBackgroundResource(0);
			getCameraManager().startCamera();
		} else {
			getCameraManager().stopCamera();
			previewing = false;
		}
		
	}

	@Override
	public void onFrame(byte[] data, Size size) {
		devCaptureText.setText(size.width + " x " + size.height);
	}
}
