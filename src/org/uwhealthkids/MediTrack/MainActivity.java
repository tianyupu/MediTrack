package org.uwhealthkids.MediTrack;

import java.util.List;
import java.util.Locale;

import org.uwhealthkids.MediTrack.SignUpActivities.SignUpActivity;
import org.uwhealthkids.MediTrack.SignUpActivities.SignUpBaby;
import org.uwhealthkids.MediTrack.SignUpActivities.SignUpResetPassword;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText usernameEditText;
	private EditText passwordEditText;
	//private Context here = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		/*
		// get a default baby for testing purposes
		ParseQuery babyQuery = new ParseQuery("Baby");
		babyQuery.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
		babyQuery.whereEqualTo("objectId", "KvqFGj101E");
		babyQuery.findInBackground(new FindCallback() {
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				if (e == null) {
					if (objects.size() == 1) {
						CustomApplication.getInstance().setCurrBaby(objects.get(0));
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
		 */
		ParseUser currUser = ParseUser.getCurrentUser();
		if (currUser != null) {
			boolean isDoctor = currUser.getBoolean("doctor");
			CustomApplication.getInstance().setCurrUser(currUser);
			Log.i("MainActivity", currUser.getString("username"));
			if (isDoctor) {
				Intent intent = new Intent(CustomApplication.getInstance(), DoctorMainActivity.class);
				startActivity(intent);
			}
			else {
				if (hasBaby(currUser.getObjectId())) {
					Intent intent = new Intent(CustomApplication.getInstance(), PatientActivity.class);
					startActivity(intent);
				}
				else {
					Intent intent = new Intent(CustomApplication.getInstance(), SignUpBaby.class);
					startActivity(intent);
				}
			}
			finish();
		}
		else {
			setContentView(R.layout.activity_main);

			TextView pwReset = (TextView)findViewById(R.id.pwResetText);

			pwReset.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(CustomApplication.getInstance(), SignUpResetPassword.class);
					startActivity(intent);

				}
			});
		}





	}




	/*
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
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	 */

	public void onSignupButtonClicked(View v) {
		Intent intent = new Intent(this, SignUpActivity.class);
		startActivity(intent);
	}

	public void onLoginButtonClicked(View v) {
		usernameEditText = (EditText) findViewById(R.id.username_edittext);
		passwordEditText = (EditText) findViewById(R.id.password_edittext);

		String username = usernameEditText.getText().toString().toLowerCase(Locale.US);
		String password = passwordEditText.getText().toString();


		ParseUser.logInInBackground(username, password, new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				if (user != null) {
					String userId = user.getObjectId();
					CustomApplication.getInstance().setCurrUser(user);
					if (user.getBoolean("doctor")) {
						Intent intent = new Intent(CustomApplication.getInstance(), DoctorMainActivity.class);
						startActivity(intent);
					}
					else {
						if (hasBaby(userId) == true) {
							Intent intent = new Intent(CustomApplication.getInstance(), PatientActivity.class);
							startActivity(intent);
						}
						else {
							Intent intent = new Intent(CustomApplication.getInstance(), SignUpBaby.class);
							startActivity(intent);
						}
					}
					finish();

				} else {
					Context context = CustomApplication.getInstance();
					CharSequence text = e.getMessage();
					int duration = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
			}
		});
	}
	/*
	public void onAddButtonClicked(View v) {
		Intent intent = new Intent(this, AddActivity.class);
		startActivity(intent);
	}

	public void onViewButtonClicked(View v) {
		Intent intent = new Intent(this, ViewActivity.class);
		startActivity(intent);
	}

	public void onSummButtonClicked(View v){
		Intent intent = new Intent(this, SummActivity.class);
		startActivity(intent);
	}
	 */
	/**
	 * hasBaby method checks if the parent has a baby in the records. 
	 */
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
