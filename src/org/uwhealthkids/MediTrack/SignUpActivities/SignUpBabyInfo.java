package org.uwhealthkids.MediTrack.SignUpActivities;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import org.uwhealthkids.MediTrack.*;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
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
import android.widget.Toast;



public class SignUpBabyInfo extends Activity{
	private static final int SELECT_PHOTO = 100;
	TextView textTargetUri;
	ImageView targetImage;
	ImageButton buttonLoadImage;
	ParseFile imageFile;
	Boolean babyMade;
	ParseObject baby = new ParseObject("Baby");

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

		Intent intent = new Intent(this, SignUpPickDoc.class);
		startActivity(intent);
	}

	private void createBaby(){
		try{
			//initializes the gender to female
			boolean female = true;
			babyMade = false;

			// initializes the baby name
			EditText babyname =  (EditText) findViewById(R.id.babyname);
			EditText babySurname = (EditText) findViewById(R.id.babysurname);
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
			
			baby.put("fname", babyname.getText().toString());
			baby.put("surname", babySurname.getText().toString());
			baby.put("dob", cal.getTime());
			baby.put("female", female);
			if(imageFile != null){
				baby.put("baby_pic", imageFile);
			}
			baby.save();
			babyMade = true;

			//makes the newly created baby the current baby
			CustomApplication.getInstance().setCurrBaby(baby);

			//creates a relationship object and puts the user and baby together
			ParseUser user= ParseUser.getCurrentUser();
			ParseObject Rel = new ParseObject("BabyUserRel");
			Rel.put("baby", baby);
			Rel.put("user", user);	
			Rel.save();
		} catch (ParseException e) {
			if(babyMade == true){
				try {
					baby.delete();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}			
			}
			Toast.makeText(CustomApplication.getInstance(), "Couldn't establish an Internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
		}


	}


	public void button1Pressed(View v) { 
		Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
		startActivityForResult(intent, SELECT_PHOTO);


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

		switch(requestCode) { 
		case SELECT_PHOTO:
			if(resultCode == RESULT_OK){  
				Uri selectedImage = imageReturnedIntent.getData();
				InputStream imageStream1;
				InputStream imageStream;
				byte[] inputData;


				try {
					imageStream = getContentResolver().openInputStream(selectedImage);		
					inputData = getBytes(imageStream);
					imageFile = new ParseFile("babypic.jpg", inputData);
					imageFile.saveInBackground();


					imageStream1 = getContentResolver().openInputStream(selectedImage);		
					Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream1);
					ImageView imageShowing = (ImageView)findViewById(R.id.babyPicImage);
					imageShowing.setImageBitmap(yourSelectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
	public byte[] getBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}
		return byteBuffer.toByteArray();
	}



}



