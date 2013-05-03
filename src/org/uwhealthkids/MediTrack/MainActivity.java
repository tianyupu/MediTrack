package org.uwhealthkids.MediTrack;
import java.util.List;

import org.uwhealthkids.MediTrack.SignUpActivities.SignUpActivity;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText usernameEditText;
	private EditText passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
    	ParseQuery query = new ParseQuery("Baby");
		query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
    	query.whereEqualTo("objectId", "KvqFGj101E");
    	query.findInBackground(new FindCallback() {
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onSignupButtonClicked(View v) {
		
		Intent intent = new Intent(this, SignUpActivity.class);
		startActivity(intent);
		
	}

	public void onLoginButtonClicked(View v) {
		usernameEditText = (EditText) findViewById(R.id.username_edittext);
		passwordEditText = (EditText) findViewById(R.id.password_edittext);

		String username = usernameEditText.getText().toString();
		String password = passwordEditText.getText().toString();

		Intent intent = new Intent(this, PatientActivity.class);
		startActivity(intent);
	}

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
}
