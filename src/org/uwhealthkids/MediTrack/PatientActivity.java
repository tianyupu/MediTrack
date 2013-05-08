package org.uwhealthkids.MediTrack;

import java.util.List;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PatientActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_main);
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
/*
		ParseQuery babyRel = new ParseQuery("BabyUserRel");
		ParseObject user = CustomApplication.getInstance().getCurrUser();
		
		babyRel.whereEqualTo("user", user);
		try {
			List<ParseObject> babies = babyRel.find();
			ParseObject currBaby = babies.get(0);
			CustomApplication.getInstance().setCurrBaby(currBaby);
		} catch (ParseException e1) {
			Toast.makeText(CustomApplication.getInstance(), e1.toString(), Toast.LENGTH_LONG).show();
		}
*/
		TextView babyNameHolder = (TextView) findViewById(R.id.babyname);
		String babyName = CustomApplication.getInstance().getCurrBaby().getString("fname");
		babyName = babyName + " " + CustomApplication.getInstance().getCurrBaby().getString("surname");
		babyNameHolder.setText(babyName);
	}

	public void onAddButtonClicked(View v) {
    	Intent intent = new Intent(this, AddActivity.class);
    	startActivity(intent);
	}
	
	public void onSummButtonClicked(View v) {
    	Intent intent = new Intent(this, SummActivity.class);
    	startActivity(intent);
	}
	
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	        	startActivity(new Intent(this, Preferences.class));
	        	return true;
	        case R.id.action_logout:
	        	ParseUser.logOut();
	        	startActivity(new Intent(this, MainActivity.class));
	        	finish();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
