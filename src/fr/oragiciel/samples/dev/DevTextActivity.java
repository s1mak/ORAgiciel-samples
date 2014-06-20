package fr.oragiciel.samples.dev;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;
import fr.oragiciel.samples.R;

public class DevTextActivity extends AbstractActivity {

	private int[] colors = { Color.rgb(255, 0, 0), Color.rgb(0, 255, 0),
			Color.rgb(0, 0, 255), Color.rgb(255, 255, 255),
			Color.rgb(127, 127, 127) };
	private int indexColor = 0;
	private TextView textSize;
	private TextView textSizeValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_text);

		textSize = (TextView) findViewById(R.id.textSize);
		textSizeValue = (TextView) findViewById(R.id.textSizeValue);
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < 2000; i++) {
			for (int j = 1; j <= 10; j++) {
				builder.append(j % 10);
			}
		}
		textSize.setText(builder.toString());
		refresh();

	}

	@Override
	public void onMoveUp() {
		textSize.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				textSize.getTextSize() + 1);

		refresh();
	}

	private void refresh() {
		textSizeValue.setText(textSize.getTextSize() + "px \n");
	}

	@Override
	public void onMoveDown() {
		textSize.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				textSize.getTextSize() - 1);
	}

	@Override
	public void onMoveForward() {
		indexColor++;
		if (indexColor >= colors.length) {
			indexColor = 0;
		}
		textSize.setTextColor(colors[indexColor]);
	}

	@Override
	public void onMoveBackward() {
		indexColor--;
		if (indexColor < 0) {
			indexColor = colors.length - 1;
		}
		textSize.setTextColor(colors[indexColor]);
	}
}
