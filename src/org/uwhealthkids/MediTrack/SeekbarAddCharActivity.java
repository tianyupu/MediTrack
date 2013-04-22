package org.uwhealthkids.MediTrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekbarAddCharActivity extends AddActivity {
	SeekBar seekBar;
	TextView textView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		
		setContentView(super.layouts[id]);
		
		seekBar = (SeekBar) findViewById(R.id.char_data);
		textView = (TextView) findViewById(R.id.slider_value);
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				textView.setText(progress+"%");
			}
			public void onStartTrackingTouch(SeekBar arg0) {
				
			}
			public void onStopTrackingTouch(SeekBar arg0) {
				
			}
		});
		Log.i("SeekbarAddCharActivity", "created and set seekbar");
	}
}
