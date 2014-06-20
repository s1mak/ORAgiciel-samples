package fr.oragiciel.samples.dev;

import fr.oragiciel.sdk.activity.OraSimpleTouchActivity;

public class AbstractActivity extends OraSimpleTouchActivity {

	long doubleTouchTime = -1;
	
	@Override
	public void onDoubleTouch() {
		long time = System.currentTimeMillis();
		if (time - doubleTouchTime < 1000) {
			finish();
		}
		doubleTouchTime = time;
	}
}
