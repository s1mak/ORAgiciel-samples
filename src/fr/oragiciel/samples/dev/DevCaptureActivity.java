package fr.oragiciel.samples.dev;

import java.io.IOException;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import fr.oragiciel.samples.R;

public class DevCaptureActivity extends AbstractActivity {

	private boolean previewing;
	private Camera camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_capture);
		previewing = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;
			previewing = false;
		}
	}

	@Override
	public void onTouch() {
		if (!previewing) {
			SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
			SurfaceHolder surfaceHolder = surfaceView.getHolder();
			// surfaceHolder.addCallback(new CameraCallBack());
			surfaceView.setBackgroundResource(0);
			camera = Camera.open();
			if (camera != null) {
				try {
//					camera.setDisplayOrientation(getOrientationDegree());
					camera.setPreviewDisplay(surfaceHolder);
					camera.startPreview();
					previewing = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			camera.stopPreview();
			camera.release();
			camera = null;
			previewing = false;
		}
		
	}
}
