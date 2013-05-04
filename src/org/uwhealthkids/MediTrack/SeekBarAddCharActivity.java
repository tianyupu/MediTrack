package org.uwhealthkids.MediTrack;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekBarAddCharActivity extends AddActivity {
	protected SeekBar seekBar;
	protected TextView textView;
	protected ParseObject charObj;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		
		setContentView(AddActivity.layouts[id]);
		
		seekBar = (SeekBar) findViewById(R.id.char_data);
		textView = (TextView) findViewById(R.id.slider_value);
		textView.setText("0%");
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				textView.setText(progress+"%");
			}
			public void onStartTrackingTouch(SeekBar arg0) {
				
			}
			public void onStopTrackingTouch(SeekBar arg0) {
				
			}
		});
		
		Button saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ParseObject testRecord = new ParseObject("Record");
				testRecord.put("baby", CustomApplication.getInstance().getCurrBaby());
				testRecord.put("who_added", CustomApplication.getInstance().getCurrUser());
				testRecord.put("value1", seekBar.getProgress()/100.0);
		    	ParseQuery query = new ParseQuery("Charact");
				query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
		    	query.whereEqualTo("objectId", AddActivity.CHAR_ID_PULSEOXYGEN);
		    	try {
					charObj = query.find().get(0);
				} catch (ParseException e1) {
					Toast.makeText(CustomApplication.getInstance(), "Couldn't establish an Internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
				}
				testRecord.put("charact", charObj);
				testRecord.saveEventually(new SaveCallback() {
					@Override
					public void done(ParseException e) {
						Toast.makeText(CustomApplication.getInstance(), "Entry saved successfully", Toast.LENGTH_SHORT).show();
					}
				});
				finish();
			}
		});
	}
}
