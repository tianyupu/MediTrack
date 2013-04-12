package org.uwhealthkids.MediTrack;

import java.util.Date;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ListView;

public class SummTFrameActivity extends ListActivity {

	public static String TFRAME;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//----------------------------------------------------------
		//Used to test if information is passed correctly with intent
		// Get the message from the intent
		/**
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
		 */
		//-----------------------------------------------------------
		String[] values = new String[] { "Last 7 Days", "Last 14 Days",
				"Last 30 Days", "Pick Date"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		TFRAME = item;
		
		if(TFRAME == "Pick Date"){
			Date today = new Date();
			DatePicker picker = new DatePicker(this);
			picker.setSpinnersShown(true);
			//picker.setEnabled(true);
			OnDateChangedListener onDateChangedListener = null;
			picker.init(today.getYear(), today.getMonth(), today.getDay(), 
					onDateChangedListener);
		}
		//Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summ_tframe, menu);
		return true;
	}

	/**
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
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
			PopupMenu pickDates = new PopupMenu(this, view);
		    MenuInflater inflater = pickDates.getMenuInflater();
		    inflater.inflate(R.menu.summ_tframe, pickDates.getMenu());
		    pickDates.show();

		break;
		}
	}
	 */

}
