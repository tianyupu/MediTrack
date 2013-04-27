package org.uwhealthkids.MediTrack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class SummActivity extends Activity {
	
	public static ArrayList<Integer> selections = new ArrayList<Integer>();
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
		//will only pass one selection
		//TODO figure out how to pass multiple selections
		if(selections.size() != 0){
			Bundle selects = new Bundle();
			selects.putIntegerArrayList("summSelections", selections);
			toTFrame.putExtras(selects);
			/**
			CheckBox select = (CheckBox) findViewById(selections.get(0));
			String message = (String) select.getText();
			toTFrame.putExtra(EXTRA_MESSAGE, message);
			*/
		}
		startActivity(toTFrame);
	}
	
	public void onOpSel(View view){
		selections.add(view.getId());
	}

}
