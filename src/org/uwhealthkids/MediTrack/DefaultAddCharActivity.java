package org.uwhealthkids.MediTrack;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DefaultAddCharActivity extends AddActivity {
	protected ParseObject charObj;
	protected int charId;
	public static final double LB_TO_KG_CONV_FACTOR = 0.4535;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		charId = id;
		
		setContentView(AddActivity.layouts[id]);
		
		if (AddActivity.layouts[id] == R.layout.add_weight) {
			Spinner unitSpinner = (Spinner) findViewById(R.id.weight_units_spinner);
			ArrayAdapter<CharSequence> unitSpinnerAdapt = 
					ArrayAdapter.createFromResource(unitSpinner.getContext(), R.array.weight_units_array, android.R.layout.simple_spinner_item);
			unitSpinnerAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			unitSpinner.setAdapter(unitSpinnerAdapt);
		}
		
		Button saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				TextView textView = (TextView) findViewById(R.id.char_data);
				ParseObject testRecord = new ParseObject("Record");
				testRecord.put("baby", CustomApplication.getInstance().getCurrBaby());
				testRecord.put("who_added", CustomApplication.getInstance().getCurrUser());
				testRecord.put("value1", Double.parseDouble(textView.getText().toString()));
				
		    	ParseQuery query = new ParseQuery("Charact");
				query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
				if (AddActivity.layouts[charId] == R.layout.add_heartrate)
					query.whereEqualTo("objectId", AddActivity.CHAR_ID_HEARTRATE);
				else if (AddActivity.layouts[charId] == R.layout.add_weight) {
		    		query.whereEqualTo("objectId", AddActivity.CHAR_ID_WEIGHT);
		    		Spinner weightUnits = (Spinner) findViewById(R.id.weight_units_spinner);
		    		String units = weightUnits.getSelectedItem().toString();
		    		if (units.equals("kg")) { // units in kg, so store as is
		    			testRecord.put("value1", Double.parseDouble(textView.getText().toString()));
		    		}
		    		else { // units in lb, so convert to kg and store
		    			testRecord.put("value1", LB_TO_KG_CONV_FACTOR * Double.parseDouble(textView.getText().toString()));
		    		}
				}
		    	else if (AddActivity.layouts[charId] == R.layout.add_bloodpressure) {
		    		query.whereEqualTo("objectId", AddActivity.CHAR_ID_BLOODPRESSURE);
		    		TextView text2 = (TextView) findViewById(R.id.char_data1);
		    		testRecord.put("value2", Double.parseDouble(text2.getText().toString()));
		    	}
		    	try {
					charObj = query.find().get(0);
				} catch (ParseException e1) {
					Toast.makeText(CustomApplication.getInstance(), "Couldn't establish an Internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
					finish();
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