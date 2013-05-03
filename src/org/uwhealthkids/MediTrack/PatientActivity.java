package org.uwhealthkids.MediTrack;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PatientActivity extends Activity {
	ParseObject currBaby = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_main);
    	ParseQuery query = new ParseQuery("Baby");
    	query.whereEqualTo("objectId", "KvqFGj101E");
    	query.findInBackground(new FindCallback() {
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					if (objects.size() == 1) {
						currBaby = objects.get(0);
					}
					else {
						Log.i("PatientActivity", "why the hell do I get more than 1 baby");
					}
				}
				else {
					Log.i("PatientActivity", "error fetching baby");
				}
			}
    	});
	}

	public void onAddButtonClicked(View v) {
    	Intent intent = new Intent(this, AddActivity.class);
    	
    	if (currBaby != null) {
    		//intent.putExtra("baby", currBaby);
    	}
    	startActivity(intent);
	}
	/*	
	public void onSummButtonClicked(View v) {
    	Intent intent = new Intent(this, SummActivity.class);
    	startActivity(intent);
	}
	*/
	public void onViewButtonClicked(View v) {
    	Intent intent = new Intent(this, ViewActivity.class);
    	startActivity(intent);
	}
	
/*	
	public void onEditButtonClicked(View v) {
    	Intent intent = new Intent(this, EditActivity.class);
    	startActivity(intent);
	}
	*/
}
