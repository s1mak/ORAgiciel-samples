package fr.oragiciel.samples.dev;

import java.util.LinkedList;
import java.util.Queue;

import android.os.Bundle;
import android.widget.TextView;
import fr.oragiciel.samples.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class DevActionActivity extends AbstractActivity {

	private TextView textMove;
	
	private Queue<String> queueLog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		queueLog = new LinkedList<String>();

		setContentView(R.layout.dev_action);		
		
		textMove = (TextView) findViewById(R.id.textMove);
		
	}
		
	public void addText(String text) {
		if(queueLog.size() >= 7) {
			queueLog.remove();
		}
		queueLog.add(text);
		String tmp = "";
		for (String t : queueLog) {
			tmp += t + "\n";
		}
		textMove.setText(tmp);
	}
	


		@Override
		public void onTouch() {
			addText("touch");
		}

		@Override
		public void onLongPress() {
			addText("long touch");	
		}

		@Override
		public void onDoubleTouch() {
			super.onDoubleTouch();
			addText("double touch");	
		}

		@Override
		public void onMoveUp() {
			addText("up");
		}

		@Override
		public void onMoveDown() {
			addText("down");
		}

		@Override
		public void onMoveForward() {
			addText("forward");
		}

		@Override
		public void onMoveBackward() {
			addText("backward");
		}
		
}
