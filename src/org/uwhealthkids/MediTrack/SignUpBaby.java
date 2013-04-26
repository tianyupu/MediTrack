package org.uwhealthkids.MediTrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpBaby extends Activity{

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.sign_up_baby);

			//Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");

		}
		public void onNextButtonClicked(View v) {
	    	Intent intent = new Intent(this, PatientActivity.class);
	    	startActivity(intent);
		}



}
