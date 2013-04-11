package org.uwhealthkids.MediTrack;

import android.os.Bundle;
import android.app.Activity;
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
		
		usernameEditText = (EditText) findViewById(R.id.username_edittext);
		passwordEditText = (EditText) findViewById(R.id.password_edittext);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onSignUpButtonClicked(View v) {
		
		
	}
	
	public void onLoginButtonClicked(View v) {
		
	}
}
