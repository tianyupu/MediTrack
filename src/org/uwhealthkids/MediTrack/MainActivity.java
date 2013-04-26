package org.uwhealthkids.MediTrack;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
		
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onSignUpButtonClicked(View v) {
		usernameEditText = (EditText) findViewById(R.id.username_edittext);
		passwordEditText = (EditText) findViewById(R.id.password_edittext);

		String username = usernameEditText.getText().toString();
		String password = passwordEditText.getText().toString();
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
}
