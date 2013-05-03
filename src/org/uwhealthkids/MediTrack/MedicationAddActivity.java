package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.Parse;
import com.parse.SaveCallback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MedicationAddActivity extends AddActivity {
	public ArrayList<String> medicationNames = new ArrayList<String>();
	public ArrayAdapter<String> adapter;
	public ArrayList<ParseObject> medicationObjs = new ArrayList<ParseObject>();
	public ParseObject charObj;
	
	public void onCreate(Bundle savedBundleInstance) {
		super.onCreate(savedBundleInstance);
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		// get the id to find the view to inflate
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		setContentView(AddActivity.layouts[id]);
		
		// find the spinner and set its adapter
		Spinner spinner = (Spinner) findViewById(R.id.medication_spinner);
		adapter = new ArrayAdapter<String>(spinner.getContext(), android.R.layout.simple_spinner_item, medicationNames);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		medicationNames.add("Select...");
		
		// populate the spinner dropdown with medication from the cloud database
		ParseQuery query = new ParseQuery("Medication");
		query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
		query.findInBackground(new FindCallback() {
			@Override
			public void done(List<ParseObject> medicationList, ParseException e) {
				if (e == null) {
					for (ParseObject obj : medicationList) {
						String name = obj.getString("name");
						if (!medicationNames.contains(name)) {
							medicationNames.add(name);
							medicationObjs.add(obj);
						}
					}
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(CustomApplication.getInstance(), "Couldn't establish an Internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		Button saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				TimePicker timePicker = (TimePicker) findViewById(R.id.char_data);
				TextView text = (TextView) findViewById(R.id.char_data1);
				Spinner spinner = (Spinner) findViewById(R.id.medication_spinner);
				int position = spinner.getSelectedItemPosition();
				ParseObject selectedObj = medicationObjs.get(position);
				Calendar cal = Calendar.getInstance();
				cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
				ParseObject testRecord = new ParseObject("Record");
				testRecord.put("notes", text.getText().toString());
				testRecord.put("medication", selectedObj);
				testRecord.put("baby", CustomApplication.getInstance().getCurrBaby());
				testRecord.put("who_added", CustomApplication.getInstance().getCurrUser());
				testRecord.put("time", cal.getTime());
		    	ParseQuery query = new ParseQuery("Charact");
				query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
		    	query.whereEqualTo("objectId", AddActivity.CHAR_ID_MEDICATION);
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
