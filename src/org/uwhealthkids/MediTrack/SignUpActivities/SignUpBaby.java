package org.uwhealthkids.MediTrack.SignUpActivities;

import org.uwhealthkids.MediTrack.AddActivity;
import org.uwhealthkids.MediTrack.PatientActivity;
import org.uwhealthkids.MediTrack.R;
import org.uwhealthkids.MediTrack.R.id;
import org.uwhealthkids.MediTrack.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpBaby extends Activity{
	RadioButton rd1;
	RadioButton rd2;
	RadioGroup radioBabyGroup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_baby);

		//Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");

	}
	public void onNextButtonClicked(View v) {
		RadioButton rd1 = (RadioButton) findViewById(R.id.hasRec);
		RadioButton rd2 = (RadioButton) findViewById(R.id.newRec);
		RadioGroup radioBabyGroup;
		
		radioBabyGroup = (RadioGroup) findViewById(R.id.babyRadioGroup);
		int checkedID = radioBabyGroup.getCheckedRadioButtonId();
		if(checkedID == rd1.getId()){
			Intent intent = new Intent(this, AddActivity.class);
			startActivity(intent);
		}
		if(checkedID == rd2.getId()){
			Intent intent = new Intent(this, PatientActivity.class);
			startActivity(intent);
		}
	}






}