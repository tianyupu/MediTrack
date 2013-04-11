package org.uwhealthkids.MediTrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SummTFrameActivity extends Activity {
	
	public static int TFRAME;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summ_tframe);

		//----------------------------------------------------------
		//Used to test if information is passed correctly with intent
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(SummActivity.EXTRA_MESSAGE);

	    // Create the text view
	    if(message != null){
	    	TextView textView = new TextView(this);
	    	textView.setTextSize(40);
	    	textView.setText(message);

	    	// Set the text view as the activity layout
	    	setContentView(textView);
	    }
	    //-----------------------------------------------------------
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summ_tframe, menu);
		return true;
	}
	
	public void tFrameTapped(View view){
		int id = view.getId();
		
		switch(id){
		
		case R.id.last_7:
			TFRAME = 7;
			
		case R.id.last_14:
			TFRAME = 14;
			
		case R.id.last_30:
			TFRAME = 30;
			
		case R.id.pick_date:
			
			
		break;
		}
	}

}
