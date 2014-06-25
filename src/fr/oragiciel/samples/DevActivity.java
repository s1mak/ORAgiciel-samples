package fr.oragiciel.samples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import fr.oragiciel.samples.dev.DevActionActivity;
import fr.oragiciel.samples.dev.DevCaptureActivity;
import fr.oragiciel.samples.dev.DevColorActivity;
import fr.oragiciel.samples.dev.DevQRActivity;
import fr.oragiciel.samples.dev.DevScreenInfoActivity;
import fr.oragiciel.samples.dev.DevTextActivity;
import fr.oragiciel.sdk.activity.OraSimpleTouchActivity;

public class DevActivity extends OraSimpleTouchActivity {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_main);

		findViewById(R.id.btnDevMove).requestFocus();

		findViewById(R.id.btnDevMove).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DevActivity.this,
						DevActionActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btnDevColor).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(DevActivity.this,
								DevColorActivity.class);
						startActivity(intent);
					}
				});

		findViewById(R.id.btnDevCapture).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(DevActivity.this,
								DevCaptureActivity.class);
						startActivity(intent);
					}
				});

		findViewById(R.id.btnDevText).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DevActivity.this,
						DevTextActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btnDevScreenInfo).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(DevActivity.this,
								DevScreenInfoActivity.class);
						startActivity(intent);
					}
				});

		findViewById(R.id.btnDevQR).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(DevActivity.this,
								DevQRActivity.class);
						startActivity(intent);
					}
				});
	}

	@Override
	public void onMoveDown() {
		if (getCurrentFocus().getNextFocusDownId() != View.NO_ID) {
			findViewById(getCurrentFocus().getNextFocusDownId()).requestFocus();
		}
	}

	@Override
	public void onMoveUp() {
		if (getCurrentFocus().getNextFocusUpId() != View.NO_ID) {
			findViewById(getCurrentFocus().getNextFocusUpId()).requestFocus();
		}
	}

	@Override
	public void onTouch() {
		if (getCurrentFocus() instanceof Button) {
			((Button) getCurrentFocus()).callOnClick();
		}
	}
	
	@Override
	public void onDoubleTouch() {
		finish();
	}
}
