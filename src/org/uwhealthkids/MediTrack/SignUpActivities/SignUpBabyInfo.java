package org.uwhealthkids.MediTrack.SignUpActivities;

import java.util.Calendar;

import org.uwhealthkids.MediTrack.PatientActivity;
import org.uwhealthkids.MediTrack.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;


public class SignUpBabyInfo extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_baby_info);
		
	}
	@SuppressLint("NewApi")
	public void onPickDateClicked(View v){
		 DialogFragment newFragment = new DatePickerFragment();
		    newFragment.show(getFragmentManager(), "datePicker");
		
	}
}

