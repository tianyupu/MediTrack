package org.uwhealthkids.MediTrack.SignUpActivities;

import org.uwhealthkids.MediTrack.CustomApplication;
import org.uwhealthkids.MediTrack.PatientActivity;
import org.uwhealthkids.MediTrack.R;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpFindBaby extends Activity {

	ParseObject baby;
	ParseQuery babies;
	Button foundBaby;
	TextView BabyName;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_find_baby);
		//Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");

	}
	public void onFindBabyClicked(View v){
		
		EditText babyId = (EditText)findViewById(R.id.babyId);
		String babyIdStr = babyId.getText().toString();
		BabyName = (TextView)findViewById(R.id.foundBabyName);
		if(babyIdStr.length()==10){
			if(getFromDataBase(babyIdStr)){

				String babyName = baby.get("fname").toString() + " " + baby.get("surname").toString();
				BabyName.setText(babyName);
				foundBaby = (Button)findViewById(R.id.foundBabyButton); 
				foundBaby.setVisibility(View.VISIBLE);
				
			}			
		}else{
			Toast.makeText(CustomApplication.getInstance(), "No baby found in database", Toast.LENGTH_LONG).show();
		}
	}


	private boolean getFromDataBase(String babyId){
		try {
			babies = new ParseQuery("Baby");
			baby = babies.get(babyId);
			return true; 
		} catch (ParseException e) {
			return false;
		}
	}

	public void onFoundBabyClicked(View v){
		
		try {
		
		//creates a relationship object and puts the user and baby together
		ParseUser user= ParseUser.getCurrentUser();
		ParseObject Rel = new ParseObject("BabyUserRel");
		Rel.put("baby", baby);
		Rel.put("user", user);	
		Rel.save();
		
		CustomApplication.getInstance().setCurrBaby(baby);
		Intent intent = new Intent(this, PatientActivity.class);
		startActivity(intent);

		finish();
		
		} catch (ParseException e) {
			Toast.makeText(CustomApplication.getInstance(), "Couldn't establish an Internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();

		}
	}



}
