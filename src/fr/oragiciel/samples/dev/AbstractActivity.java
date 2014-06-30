package fr.oragiciel.samples.dev;

import fr.oragiciel.sdk.activity.OraSimpleTouchActivity;

public class AbstractActivity extends OraSimpleTouchActivity {

	private long doubleTouchTime = -1;
	private boolean doubleDoubleTouch = false;

	@Override
	public void onDoubleTouch() {
		if (doubleDoubleTouch) {
			long time = System.currentTimeMillis();
			if (time - doubleTouchTime < 1000) {
				finish();
			}
			doubleTouchTime = time;
		} else {
			finish();
		}
	}

	protected void setDoubleDoubleTouch(boolean doubleDoubleTouch) {
		this.doubleDoubleTouch = doubleDoubleTouch;
	}
	
	
}
