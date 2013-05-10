package org.uwhealthkids.MediTrack.SignUpActivities;

import org.uwhealthkids.MediTrack.*;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity{
	EditText email;
	EditText confEmail;
	EditText fname;
	EditText surname;
	EditText password;
	EditText confPassword;
	Context here = this;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");

	}
	public void onnextButtonClicked(View v) {
		
		if(validInfo()){
			
			// if all the entered information is valid, then the user can go and sign-up
			boolean doctor = false;
			ParseUser user = new ParseUser();
			user.setUsername(email.getText().toString());
			user.setPassword(password.getText().toString());
			user.setEmail(email.getText().toString());
			user.put("fname", fname.getText().toString());
			user.put("surname", surname.getText().toString());
			user.put("doctor", doctor);
			user.signUpInBackground(new SignUpCallback() {
				public void done(ParseException e) {
					//Sign up was successful taken to the log in screen
					if (e == null) {
						ParseUser.logOut();
						Intent intent = new Intent(here,MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						
						Context context = getApplicationContext();
						int duration = Toast.LENGTH_LONG;
						CharSequence text = e.getMessage();;
						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
						// Sign up didn't succeed. Look at the ParseException
						// to figure out what went wrong
					}
				}
			});


				
		}

	}


	private boolean validInfo(){
		boolean valid = true;

		//Get the editable texts
		email = (EditText)findViewById(R.id.emailad);
		confEmail = (EditText) findViewById(R.id.confemail);
		fname = (EditText)findViewById(R.id.fname);
		surname = (EditText)findViewById(R.id.surname);
		password = (EditText)findViewById(R.id.password);
		confPassword = (EditText)findViewById(R.id.confpassword);

		//Create a toast to give error message
		Context context = getApplicationContext();
		CharSequence text = "Creating User";
		int duration = Toast.LENGTH_LONG;

		//Verify that the information that they put in is usable
		if(!checkString(fname.getText().toString())){
			valid = false;
			text = fname.getText().toString();
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}

		if(!checkString(surname.getText().toString())){
			valid = false;
			text = "Not a valid last name";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		if(!confEmail.getText().toString().equalsIgnoreCase(email.getText().toString())){
			valid = false;
			text = "The emails does not match";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}

		if(!confPassword.getText().toString().equalsIgnoreCase(password.getText().toString())){
			valid = false;
			text = "The password does not match";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		if(!checkPassword(password.getText().toString())){
			valid = false;
			text = "The password must contain a number";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}

		//Toast toast = Toast.makeText(context, text, duration);
		//toast.show();


		return valid;

	}

	//Returns true if password contains a number
	private boolean checkPassword(String password){
		boolean validPassword = true;
		if(!password.matches(".*\\d.*")){
			validPassword=false;   
		} 
		return validPassword;
	}

	private boolean checkString(String name){
		boolean validName = true; // boolean that would be sent if it is a valid name

		//changes it to false if the name is longer than 30 characters
		if(name.length()>30){
			validName=false;
		}

		//changes it to false if there is a digit in the string
		if(name.matches(".*\\d.*")){
			validName=false;   
		} 

		return validName;
	}
}

//


