package fr.oragiciel.samples;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.oragiciel.sdk.activity.OraSimpleTouchActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class PhoneActivity extends OraSimpleTouchActivity {

	private Button currentButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ora_phone);
		
		currentButton = (Button) findViewById(R.id.button5);
		
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	public void onDoubleTouch() {
		finish();
	}

	@Override
	public void onTouch() {
		EditText text = (EditText) findViewById(R.id.phoneText);
		text.append(currentButton.getText());
	}

	@Override
	public void onMoveUp() {
		// Se déplace vers le haut si possible
		if (currentButton.getNextFocusUpId() != -1) {
			currentButton = (Button) findViewById(currentButton.getNextFocusUpId());
			currentButton.requestFocus();
		}
	}

	@Override
	public void onMoveDown() {
		// Se déplace vers le bas si possible
		if (currentButton.getNextFocusDownId() != -1) {
			currentButton = (Button) findViewById(currentButton.getNextFocusDownId());
			currentButton.requestFocus();
		}
	}

	@Override
	public void onMoveForward() {
		// Se déplace vers la droite si possible
		if (currentButton.getNextFocusRightId() != -1) {
			currentButton = (Button) findViewById(currentButton.getNextFocusRightId());
			currentButton.requestFocus();
		}
	}

	@Override
	public void onMoveBackward() {
		// Se déplace vers la gauche si possible
		if (currentButton.getNextFocusLeftId() != -1) {
			currentButton = (Button) findViewById(currentButton.getNextFocusLeftId());
			currentButton.requestFocus();
		}
	}
		
}
