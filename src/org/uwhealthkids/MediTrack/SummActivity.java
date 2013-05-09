package org.uwhealthkids.MediTrack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class SummActivity extends Activity {

	ArrayList<String> selections;
	public final static String EXTRA_MESSAGE = "org.uwhealthkids.MediTrack.MESSAGE";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summ);
		selections = new ArrayList<String>();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sum, menu);
		return true;
	}

	public void buttonClicked(View view){
		Intent toTFrame = new Intent(this, SummTFrameActivity.class);
		//TODO figure out how to pass multiple selections
		if(selections.size() != 0){
			Bundle selects = new Bundle();
			selects.putStringArrayList("summSelections", selections);
			toTFrame.putExtras(selects);
			Log.i("SummFrame", "Added the select bundle!!");
			/**
			CheckBox select = (CheckBox) findViewById(selections.get(0));
			String message = (String) select.getText();
			toTFrame.putExtra(EXTRA_MESSAGE, message);
			 */
			startActivity(toTFrame);
			finish();
		}
		else{
			Toast.makeText(this,  "Must select at least 1 item", Toast.LENGTH_SHORT).show();
		}
	}

	public void onOpSel(View view){
		CheckBox check = (CheckBox) view;
		int choice = view.getId();
		if(check.isChecked()){
			Log.i("SummFrame", "box is checked");
			switch(choice) {
			case R.id.heart_rate:
				selections.add("Heart Rate");
				break;
			case R.id.blood_pressure:
				selections.add("Blood Pressure");
				break;
			case R.id.pulse_oxygen:
				selections.add("Pulse Oxygen Saturation");
				break;
			case R.id.feeding_goal:
				selections.add("Feeding Goal");
				break;
			case R.id.weight:
				selections.add("Weight");
				break;
			default: break;
			}
		}
		else{
			Log.i("SummFrame", "box is unchecked");
			switch(choice) {
			case R.id.heart_rate:
				selections.remove("Heart Rate");
				break;
			case R.id.blood_pressure:
				selections.remove("Blood Pressure");
				break;
			case R.id.pulse_oxygen:
				selections.remove("Pulse Oxygen Saturation");
				break;
			case R.id.feeding_goal:
				selections.remove("Feeding Goal");
				break;
			case R.id.weight:
				selections.remove("Weight");
				break;
			default: break;
			}
			Log.i("SummFrame", "removed from" + selections);
		}
	}

}
