package com.mattayme.rpgclasschooser;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;

import com.google.android.gms.ads.*;
import com.mattayme.rpgclasschooser.R;

public class ClassDescriptionActivity extends Activity {
	
	public static final String EXTRA_CLASS_TITLE = "com.example.rpgClassChooser.chooseractivity.classTitle";
	public static final String EXTRA_CLASS_DESCRIPTION="com.example.rpgClassChooser.chooseractivity.classDescription";
	
	private String mClassTitle;
	private int mClassDescription;
	private TextView mClassTitleTextView;
	private TextView mClassDescriptionTextView;
	private Typeface titleTypeface;
	private Typeface descriptionTypeface;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_description);
		
		AdView adView = (AdView)this.findViewById(R.id.description_ad_view);
	    AdRequest adRequest = new AdRequest.Builder().build();
		
		//test ads, remove before publishing
				/*AdRequest adRequest = new AdRequest.Builder()
			    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)       // Emulator
			    .addTestDevice("749FB28E81F6431365E4D9CC31F30C2C") // My Galaxy Nexus test phone
			    .build();*/
	    adView.loadAd(adRequest);
		
		titleTypeface = Typeface.createFromAsset(getAssets(), "fonts/RAPSCALL.TTF");
		descriptionTypeface = Typeface.createFromAsset(getAssets(), "fonts/Kingthings_Calligraphica_2.ttf");
		
		mClassTitle = getIntent().getStringExtra(EXTRA_CLASS_TITLE);
		mClassDescription = getIntent().getIntExtra(EXTRA_CLASS_DESCRIPTION, 0);
		
		mClassTitleTextView = (TextView) findViewById(R.id.class_title_text_view);
		mClassTitleTextView.setTypeface(titleTypeface);
		mClassDescriptionTextView = (TextView)findViewById(R.id.class_description_text_view);
		mClassDescriptionTextView.setTypeface(descriptionTypeface);
		
		mClassTitleTextView.setText(mClassTitle);
		mClassDescriptionTextView.setText(mClassDescription);
	}

}
