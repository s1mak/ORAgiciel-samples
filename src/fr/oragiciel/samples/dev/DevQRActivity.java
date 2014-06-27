package fr.oragiciel.samples.dev;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.ResultPoint;

import fr.oragiciel.samples.R;
import fr.oragiciel.sdk.qrcode.QRCodeManager;
import fr.oragiciel.sdk.qrcode.QrCodeListener;

public class DevQRActivity extends AbstractActivity implements QrCodeListener {

	private QRCodeManager qrCodeManager;
	private boolean scan = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_qr);
	}

	@Override
	protected void onResume() {
		super.onResume();
		qrCodeManager = new QRCodeManager(getCameraManager());
		qrCodeManager.setQrCodeListener(this);
	}

	@Override
	public void onQrCodeRead(final Result result) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateUI(result);
			}
		});
	}

	private void updateUI(Result result) {
		TextView textView = (TextView) findViewById(R.id.qrText);
		StringBuilder str = new StringBuilder();
		str.append("format : ").append(result.getBarcodeFormat().name());
		str.append("\ntext : ").append(result.getText());
		str.append("\npoints : ");
		ResultPoint[] points = result.getResultPoints();
		for (ResultPoint resultPoint : points) {
			str.append(resultPoint.getX()).append(",")
					.append(resultPoint.getY()).append(" / ");
		}
		textView.setText(str);
	}

	@Override
	public void onTouch() {
		if (!scan) {
			Toast.makeText(this, "go scan", Toast.LENGTH_SHORT);
			qrCodeManager.scan();
		} else {
			Toast.makeText(this, "stop scan", Toast.LENGTH_SHORT);
			qrCodeManager.stopScan();
		}
		scan = !scan;
	}

	@Override
	protected void onPause() {
		scan = false;
		qrCodeManager.stopScan();
		super.onPause();

	}
}
