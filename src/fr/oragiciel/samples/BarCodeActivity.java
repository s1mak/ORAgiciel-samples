package fr.oragiciel.samples;

import java.util.HashMap;
import java.util.Map;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.zxing.Result;

import fr.oragiciel.samples.dev.AbstractActivity;
import fr.oragiciel.sdk.qrcode.QRCodeManager;
import fr.oragiciel.sdk.qrcode.QrCodeListener;

public class BarCodeActivity extends AbstractActivity implements QrCodeListener {

	private QRCodeManager qrCodeManager;
	private boolean scan = false;
	private String previousResult;

	@Override
	protected void onResume() {
		super.onResume();
		qrCodeManager = new QRCodeManager(getCameraManager());
		qrCodeManager.setQrCodeListener(this);
		previousResult = null;
	}

	@Override
	public void onTouch() {
		if (!scan) {
			qrCodeManager.scan();
		} else {
			qrCodeManager.stopScan();
		}
		scan = !scan;
	}

	@Override
	public void onQrCodeRead(final Result result) {
		if (result.getText() == null) {
			return;
		}
		if (result.getText().equals(previousResult)) {
			return;
		}
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateUI(result);
			}
		});
		previousResult = result.getText();
	}

	private void updateUI(Result result) {
		if (result.getText().startsWith("printer:")) {
			showPrinter(result.getText());
		}
	}

	private void showPrinter(String text) {
		if (previousResult == null || !previousResult.startsWith("printer:")) {
			setContentView(R.layout.bc_printer);
		}
		Map<String, String> infos = extractMap(text);
		((TextView) findViewById(R.id.bcPrinterName)).setText("Name : "
				+ infos.get("name"));
		((ProgressBar) findViewById(R.id.bcPrinterProgressBarCyan))
				.setProgress(Integer.parseInt(infos.get("c")));
		((ProgressBar) findViewById(R.id.bcPrinterProgressBarMagenta))
				.setProgress(Integer.parseInt(infos.get("m")));
		((ProgressBar) findViewById(R.id.bcPrinterProgressBarYellow))
				.setProgress(Integer.parseInt(infos.get("y")));
		((ProgressBar) findViewById(R.id.bcPrinterProgressBarKey))
				.setProgress(Integer.parseInt(infos.get("k")));
	}

	private Map<String, String> extractMap(String text) {
		Map<String, String> result = new HashMap<String, String>();
		String[] infos = text.split(":");
		for (String info : infos) {
			String[] keyValue = info.split("=");
			if (keyValue.length == 2) {
				result.put(keyValue[0], keyValue[1]);
			}
		}
		return result;
	}

	@Override
	protected void onPause() {
		scan = false;
		qrCodeManager.stopScan();
		super.onPause();
	}
}
