package fr.oragiciel.samples.dev;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;
import fr.oragiciel.samples.R;

public class DevScreenInfoActivity extends AbstractActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_screen_info);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        Point realSize = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(realSize);
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		((TextView) findViewById(R.id.textscreen)).setText(realSize.x + " x " + realSize.y);
		((TextView) findViewById(R.id.textDisplay)).setText(displayMetrics.widthPixels + " x " + displayMetrics.heightPixels);

		((TextView) findViewById(R.id.textDpi)).setText(""+displayMetrics.density + "  /  " + displayMetrics.densityDpi);
	}
}
