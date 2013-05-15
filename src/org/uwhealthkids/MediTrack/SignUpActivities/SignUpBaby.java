package org.uwhealthkids.MediTrack.SignUpActivities;

import java.util.ArrayList;
import java.util.List;

import org.uwhealthkids.MediTrack.AddActivity;
import org.uwhealthkids.MediTrack.CustomApplication;
import org.uwhealthkids.MediTrack.DoctorMainActivity;
import org.uwhealthkids.MediTrack.PatientActivity;
import org.uwhealthkids.MediTrack.R;
import org.uwhealthkids.MediTrack.R.id;
import org.uwhealthkids.MediTrack.R.layout;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUpBaby extends Activity{
	RadioButton rdhas;
	RadioButton rdnew;
	RadioButton docOption;
	RadioGroup radioBabyGroup;
	ParseUser currUser;
	EditText docCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_baby);
		docOption = (RadioButton)findViewById(R.id.doctorreg);
		currUser = ParseUser.getCurrentUser();
		docCode = (EditText)findViewById(R.id.doctorCodeEdit);
		//Finds radio buttons and radio group
		rdhas = (RadioButton) findViewById(R.id.hasRec);
		rdnew = (RadioButton) findViewById(R.id.newRec);
		radioBabyGroup = (RadioGroup) findViewById(R.id.babyRadioGroup);
		//Checks if the current user has baby's if they do, then the doctor option is hidden.
		if(hasBaby(currUser.getObjectId())){
			docOption.setVisibility(View.INVISIBLE);
		}

		docOption.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				docCode.setVisibility(View.VISIBLE);
			}
		});
		rdhas.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				docCode.setVisibility(View.INVISIBLE);
			}
		});
		rdnew.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				docCode.setVisibility(View.INVISIBLE);
			}
		});
		
		
		


		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
	}
	
	public void onNextButtonClicked(View v) {

		//Finds which radio button was selected
		int checkedID = radioBabyGroup.getCheckedRadioButtonId();

		//Checks if the selected matches the has a record radio button. If it does launches the find baby page.
		//Then it checks if the selected matches the new record radio button. If it matches the second one it 
		// launches the the sign up baby info activity. If neither it is assumed that they choose the doctor option
		// it then opens a edit text where they have to add a code to become doctor officially.

		if(checkedID == rdhas.getId()){
			Intent intent = new Intent(this, SignUpFindBaby.class);
			startActivity(intent);
			finish();
		}
		else if(checkedID == rdnew.getId()){
			Intent intent = new Intent(this, SignUpBabyInfo.class);
			startActivity(intent);
			finish();
		}
		else {

			if(docCode.getText().toString().equals("abc")){
				try {
					currUser.put("doctor", true);
					currUser.save();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Intent intent = new Intent(this, DoctorMainActivity.class);
				startActivity(intent);
				finish();
			}
			else{
				Toast.makeText(CustomApplication.getInstance(), "Not a Valid Doctor's Code", Toast.LENGTH_LONG).show();
			}

		}
	}


	private boolean hasBaby(String userId){
		//Initializes quaries. One to the Baby User Relationship table, and one to the user table
		ParseQuery relquery = new ParseQuery("BabyUserRel");
		ParseQuery userquery = new ParseQuery("_User");

		//Initializes userObject to null 
		ParseObject userObject = null;

		try {
			//Sets userObject to the object it retrieves from the user quary with the right Id
			userObject = userquery.get(userId);	
		} catch (ParseException e1) {
			Log.d("tag", "could not find parent");	
		}
		//makes constraint to the the relationship query to only include if user matchs the user object
		relquery.whereEqualTo("user", userObject);

		//Initializes boolean to true, assumes there are babies
		boolean hasBaby = true;
		try {
			List<ParseObject> babyList = relquery.find();
			//checks if the list is empty, if it is then sets has baby to false
			if(babyList.size()==0){
				hasBaby=false;	
			}
		} catch (ParseException e) {
			hasBaby = false;
		}
		return hasBaby;
	}
}
