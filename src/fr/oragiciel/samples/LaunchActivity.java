package fr.oragiciel.samples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.oragiciel.sdk.activity.OraSimpleTouchActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class LaunchActivity extends OraSimpleTouchActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ora_main);
		
		findViewById(R.id.btnDev).requestFocus();
		
		// Activité développeur : Outils de test de rendu sur le device cible
		findViewById(R.id.btnDev).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LaunchActivity.this, DevActivity.class);
				startActivity(intent);
			}
		});
		
		// Activité Phone
		findViewById(R.id.btnPhone).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LaunchActivity.this, PhoneActivity.class);
				startActivity(intent);
			}
		});
		
		// Activité BarCode
		findViewById(R.id.btnBarCode).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LaunchActivity.this, BarCodeActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public void onDoubleTouch() {
		finish();
	}

	@Override
	public void onTouch() {
		if (getCurrentFocus() instanceof Button) {
			((Button) getCurrentFocus()).callOnClick();
		}
	}

	@Override
	public void onMoveUp() {
		if (getCurrentFocus().getNextFocusUpId() != View.NO_ID) {
			findViewById(getCurrentFocus().getNextFocusUpId()).requestFocus();
		}
	}

	@Override
	public void onMoveDown() {
		if (getCurrentFocus().getNextFocusDownId() != View.NO_ID) {
			findViewById(getCurrentFocus().getNextFocusDownId()).requestFocus();
		}
	}
		
}
