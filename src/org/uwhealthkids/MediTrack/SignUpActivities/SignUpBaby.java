package org.uwhealthkids.MediTrack.SignUpActivities;

import java.util.ArrayList;
import java.util.List;

import org.uwhealthkids.MediTrack.AddActivity;
import org.uwhealthkids.MediTrack.CustomApplication;
import org.uwhealthkids.MediTrack.DoctorMainActivity;
import org.uwhealthkids.MediTrack.PatientActivity;
import org.uwhealthkids.MediTrack.R;
import org.uwhealthkids.MediTrack.R.id;
import org.uwhealthkids.MediTrack.R.layout;

import com.parse.ParseUser;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUpBaby extends Activity{
	RadioButton rd1;
	RadioButton rd2;
	RadioButton docOption;
	RadioGroup radioBabyGroup;
	Boolean docCodeFirst = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_baby);
		docOption = (RadioButton)findViewById(R.id.doctorreg);

		ArrayList<String> runningactivities = new ArrayList<String>();
		ActivityManager activityManager = (ActivityManager)CustomApplication.getInstance().getSystemService (Context.ACTIVITY_SERVICE); 
		List<RunningTaskInfo> services = activityManager.getRunningTasks(Integer.MAX_VALUE); 

		for (int i1 = 0; i1 < services.size(); i1++) { 
			runningactivities.add(0,services.get(i1).topActivity.toString());  
		} 
		Log.i("SignUpBaby", ""+runningactivities.size());
		if(runningactivities.contains("ComponentInfo{org.uwhealthkids.MediTrack.PatientActivity}")==true){
			docOption.setVisibility(View.INVISIBLE);
		}

		//Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");

	}
	public void onNextButtonClicked(View v) {
		ParseUser currUser = ParseUser.getCurrentUser();

		RadioButton rd1 = (RadioButton) findViewById(R.id.hasRec);
		RadioButton rd2 = (RadioButton) findViewById(R.id.newRec);
		RadioGroup radioBabyGroup;
		EditText docCode = (EditText)findViewById(R.id.doctorCodeEdit);

		radioBabyGroup = (RadioGroup) findViewById(R.id.babyRadioGroup);
		int checkedID = radioBabyGroup.getCheckedRadioButtonId();
		if(checkedID == rd1.getId()){
			Intent intent = new Intent(this, SignUpFindBaby.class);
			startActivity(intent);
			finish();

		}
		else if(checkedID == rd2.getId()){
			Intent intent = new Intent(this, SignUpBabyInfo.class);
			startActivity(intent);
			finish();
		}
		else {
			docCode.setVisibility(View.VISIBLE);
			if(!docCodeFirst){
				if(docCode.getText().toString().equals("abc")){
					boolean doctor = true;
					currUser.put("doctor", doctor);

					Intent intent = new Intent(this, DoctorMainActivity.class);
					startActivity(intent);
					finish();
				}
				else{
					Toast.makeText(CustomApplication.getInstance(), "Not a Valid Doctor's Code", Toast.LENGTH_LONG).show();
				}
			}
			docCodeFirst = false;
		}


	}
}
