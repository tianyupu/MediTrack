package org.uwhealthkids.MediTrack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class SummActivity extends Activity {

	public static ArrayList<String> selections = new ArrayList<String>();
	public final static String EXTRA_MESSAGE = "org.uwhealthkids.MediTrack.MESSAGE";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summ);
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
		}
		startActivity(toTFrame);
	}

	public void onOpSel(View view){
		int choice = view.getId();
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
		case R.id.feeding_time:
			selections.add("Feeding Time");
			break;
		case R.id.feeding_amount:
			selections.add("Feeding amount");
			break;
		case R.id.weight:
			selections.add("Weight");
			break;
		default: break;
		}
	}

}
