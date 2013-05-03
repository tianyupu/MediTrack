package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ListView;
import android.widget.Spinner;

public class SummTFrameActivity extends Activity implements OnItemSelectedListener {

	public static String TFRAME;
	private Calendar startDate;
	private Calendar endDate;
	private Bundle allInfo = new Bundle();
	private int tFrame = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("SummTFrame","Good for you, you created it");
		setContentView(R.layout.activity_summ_tframe);
		Log.i("SummTFrame", "Past setContentView!!!!!!! Fuck yeah!!");
		//----------------------------------------------------------
		//Used to test if information is passed correctly with intent
		// Get the message from the intent
		
	    Intent intent = getIntent();
	    Bundle selections = intent.getExtras();
	    Log.i("SummTFrame", "Ahh I see what's happening here");
	    if(selections != null) {
	    	allInfo.putIntegerArrayList("summSelections",
	    			selections.getIntegerArrayList("summSelections"));
	    	//Log.i("SummTFrame", "past the bundling code");
	    }
    	Log.i("SummTFrame", "past the bundling code");
	    
	    
	    /**
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
		Spinner tFrameSpinner = (Spinner) findViewById(R.id.pick_tframe);

		
		ArrayList<String> values = new ArrayList<String>();
		values.add("Nothing Selected");
		values.add("Last 7 Days");
		values.add("Last 14 Days");
		values.add("Last 30 Days");
		//String[] values = new String[] { "Last 7 Days", "Last 14 Days",
				//"Last 30 Days"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(tFrameSpinner.getContext(),
				android.R.layout.simple_spinner_item, values);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tFrameSpinner.setAdapter(adapter);
		Log.i("SummTFrame", "Bitch is running correctly");		

		tFrameSpinner.setOnItemSelectedListener(this);
	}


	/**
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		TFRAME = item;
		
		/*if(TFRAME == "Pick Date"){
			DatePicker picker = new DatePicker(this);
			picker.setSpinnersShown(true);
			//picker.setEnabled(true);
			OnDateChangedListener onDateChangedListener = null;
			picker.init(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, 
					onDateChangedListener);
		}
		//Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summ_tframe, menu);
		return true;
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		String choice = (String) parent.getItemAtPosition(pos);
		startDate = Calendar.getInstance();
		endDate = Calendar.getInstance();
		
		
		if(choice == "Nothing Selected") {
			onNothingSelected(parent);
		}
		
		else if(choice == "Last 7 Days"){
			endDate.add(Calendar.DATE, -7);
			Log.i("SummTFrame", "You selected something motha titmonger!!7");
		}
		else if(choice == "Last 14 Days"){
			endDate.add(Calendar.DATE, -14);
			Log.i("SummTFrame", "You selected something motha titmonger!!14");
		}
		else if(choice == "Last 30 Days"){
			endDate.add(Calendar.DATE, -30);
			Log.i("SummTFrame", "You selected something motha titmonger!!30");
		}
		
		allInfo.putSerializable("start", startDate);
		allInfo.putSerializable("end", endDate);
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		DatePicker start = (DatePicker) findViewById(R.id.startDate);
		DatePicker end = (DatePicker) findViewById(R.id.endDate);
		
		startDate.set(start.getYear(), start.getMonth(), start.getDayOfMonth());
		endDate.set(end.getYear(), end.getMonth(), end.getDayOfMonth());
		Log.i("SummTFrame","Used datepicker");
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
