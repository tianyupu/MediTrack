package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.Parse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MedicationAddActivity extends AddActivity {
	public ArrayList<String> medicationNames = new ArrayList<String>();
	public void onCreate(Bundle savedBundleInstance) {
		super.onCreate(savedBundleInstance);
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		
		setContentView(super.layouts[id]);
		
		Spinner spinner = (Spinner) findViewById(R.id.medication_spinner);
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
						}
					}
				} else {
					Log.i("MedicationAddActivity", "error retrieving medications list");
				}
			}
		});
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(spinner.getContext(), android.R.layout.simple_spinner_item, medicationNames);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
	
}
