package org.uwhealthkids.MediTrack.SignUpActivities;


import java.io.FileNotFoundException;
import java.util.Calendar;

import org.uwhealthkids.MediTrack.*;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;




public class SignUpBabyInfo extends Activity{

	TextView textTargetUri;
	ImageView targetImage;
	ImageButton buttonLoadImage;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_baby_info);
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
	}


	public void onSkipDocClicked(View v) {
		createBaby();

		Intent intent = new Intent(this, PatientActivity.class);
		startActivity(intent);
	}

	public void onDocClicked(View v){
		createBaby();

		Intent intent = new Intent(this, PatientActivity.class);
		startActivity(intent);
	}

	private void createBaby(){
		//initializes the gender to female
		boolean female = true;

		// initializes the baby name
		EditText babyname =  (EditText) findViewById(R.id.babyname);
		RadioGroup gender = (RadioGroup) findViewById(R.id.genderRadioGroup);
		RadioButton femaleButton = (RadioButton) findViewById(R.id.girl);

		// gets baby's birthday from date picker
		DatePicker dob = (DatePicker) findViewById(R.id.dobPicker);
		Calendar cal = Calendar.getInstance();
		cal.set(dob.getYear(),dob.getMonth(),dob.getDayOfMonth());


		//checks which radio button was selected and changes the female boolean if it's a male
		if(gender.getCheckedRadioButtonId() != femaleButton.getId()){
			female = false;	
		}

		//creates the baby parse object and saves it to the 
		ParseObject baby = new ParseObject("Baby");
		baby.put("fname", babyname.getText().toString());
		baby.put("dob", cal.getTime());
		baby.put("female", female);
		baby.saveInBackground();

		//makes the newly created baby the current baby
		CustomApplication.getInstance().setCurrBaby(baby);

		//creates a relationship object and puts the user and baby together
		ParseUser user= ParseUser.getCurrentUser();
		ParseObject Rel = new ParseObject("BabyUserRel");
		Rel.put("baby", baby);
		Rel.put("user", user);	
		Rel.saveInBackground();

	}


	public void button1Pressed(View v) { 
		Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data); 
		if (resultCode == RESULT_OK){
			Uri targetUri = data.getData();
			textTargetUri.setText(targetUri.toString());
			Bitmap bitmapa; 
			Bitmap bitmapb;
			try {  
				bitmapa = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
				bitmapb = Bitmap.createScaledBitmap (bitmapa,100,100,false);
				buttonLoadImage.setImageBitmap(bitmapb); 
			} catch (FileNotFoundException e) {  
				// TODO Auto-generated catch block  
				e.printStackTrace(); 
			}
		}
	}
}






