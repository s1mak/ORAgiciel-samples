package fr.oragiciel.samples;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import fr.oragiciel.adapters.CarouselAdapter;
import fr.oragiciel.sdk.activity.OraSimpleTouchActivity;
import fr.oragiciel.sdk.layout.HorizontalCarouselLayout;
import fr.oragiciel.sdk.layout.HorizontalCarouselLayout.CarouselInterface;
import fr.oragiciel.sdk.layout.HorizontalCarouselStyle;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class LaunchActivity extends OraSimpleTouchActivity {
	
	private HorizontalCarouselStyle mStyle;
	private HorizontalCarouselLayout mCarousel;
	private CarouselAdapter mAdapter;
	private ArrayList<Integer> mData = new ArrayList<Integer>(0);
	private int positionActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ora_main);
		
		mData.add(R.drawable.phone);
		mData.add(R.drawable.devscreen);
		mAdapter = new CarouselAdapter(this);
		mAdapter.setData(mData);
		mCarousel = (HorizontalCarouselLayout) findViewById(R.id.carousel_layout);
		mStyle = new HorizontalCarouselStyle(this, HorizontalCarouselStyle.STYLE_ZOOMED_OUT);		
		mCarousel.setStyle(mStyle);
		mCarousel.setAdapter(mAdapter);

		mCarousel.setTouchDetector(getOraTouchDetector());
		
		mCarousel.setOnCarouselViewChangedListener(new CarouselInterface() {
			@Override
			public void onItemChangedListener(View v, int position) {
				Toast.makeText(LaunchActivity.this, "Position " + String.valueOf(position), Toast.LENGTH_SHORT).show();
				positionActivity = position;
			}
		});
		
		mCarousel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("toto", "Touch!");
				if (positionActivity == 0) {
					Intent intent = new Intent(LaunchActivity.this, PhoneActivity.class);
					startActivity(intent);
				} else if (positionActivity == 1) {
					Intent intent = new Intent(LaunchActivity.this, DevActivity.class);
					startActivity(intent);
				}
			}
		});
	}
		
}
