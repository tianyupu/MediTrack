package org.uwhealthkids.MediTrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PatientActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_main);
	}

	public void onAddButtonClicked(View v) {
    	Intent intent = new Intent(this, AddActivity.class);
    	startActivity(intent);
	}
	/*	
	public void onSummButtonClicked(View v) {
    	Intent intent = new Intent(this, SummActivity.class);
    	startActivity(intent);
	}
	*/
	public void onViewButtonClicked(View v) {
    	Intent intent = new Intent(this, ViewActivity.class);
    	startActivity(intent);
	}
	
/*	
	public void onEditButtonClicked(View v) {
    	Intent intent = new Intent(this, EditActivity.class);
    	startActivity(intent);
	}
	*/
}
