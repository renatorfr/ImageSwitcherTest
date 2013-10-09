package com.nitryx.imageswitchertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	private ImageSwitcher imageSwitcher;
	private Button buttonNext;

	// Array of Image IDs to show in ImageSwitcher
	int imageIds[] = { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
			R.drawable.image5, R.drawable.image6 };

	int messageCount = imageIds.length;

	// To keep current Index of ImageID array
	int currentIndex = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get the references
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
		buttonNext = (Button) findViewById(R.id.buttonNext);

		// Set the ViewFactory of the ImageSwitcher that will create ImageView
		// object when asked
		imageSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// Create a new ImageView set it's properties
				ImageView imageView = new ImageView(getApplicationContext());
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT));
				return imageView;
			}
		});

		// Declare the animation and initialize them
		Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
		Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

		// Set the animation type to imageSwitcher
		imageSwitcher.setInAnimation(in);
		imageSwitcher.setOutAnimation(out);

		// ClickListener for Next button
		// When clicked on Button ImageSwitcher will switch between Images
		// The current Image will go OUT and next Image will come in with
		// specified animation
		buttonNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				currentIndex++;

				// If index reaches maximum reset it
				if (currentIndex == messageCount) {
					currentIndex = 0;
				}

				imageSwitcher.setImageResource(imageIds[currentIndex]);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
