package org.uwhealthkids.MediTrack.SignUpActivities;

import org.uwhealthkids.MediTrack.CustomApplication;
import org.uwhealthkids.MediTrack.R;

import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpResetPassword extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_reset_pw);

		//Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");

	}
	
	public void onSendEmailClicked(View v){
		EditText email = (EditText)findViewById(R.id.EmailToReset);
		String message = "You should recieve an email shortly with information about resetting your password.";
		Button sendBtn = (Button)findViewById(R.id.SendEmailButton);
		TextView displayTxt = (TextView)findViewById(R.id.resetMessage);
		
		try {
			ParseUser.requestPasswordReset(email.getText().toString());
			sendBtn.setVisibility(View.INVISIBLE);
			displayTxt.setText(message);
			email.setVisibility(View.INVISIBLE);
			
		} catch (ParseException e) {
			Toast.makeText(CustomApplication.getInstance(), e.getMessage().toString(), Toast.LENGTH_LONG).show();	
			e.printStackTrace();
		}
	
	
	
	}

}
