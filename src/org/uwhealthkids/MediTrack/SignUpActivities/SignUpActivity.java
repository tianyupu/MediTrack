package org.uwhealthkids.MediTrack.SignUpActivities;



import org.uwhealthkids.MediTrack.R;
import org.uwhealthkids.MediTrack.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		//Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
	
		
	
	
	}
	public void onnextButtonClicked(View v) {
    	Intent intent = new Intent(this, SignUpBaby.class);
    	startActivity(intent);
	}


}


