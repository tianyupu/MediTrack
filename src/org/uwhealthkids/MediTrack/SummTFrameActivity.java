package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

public class SummTFrameActivity extends Activity implements OnItemSelectedListener {

	public static String TFRAME;
	private Calendar startDate, custStart;
	private Calendar endDate, custEnd;
	private Bundle allInfo = new Bundle();
	String firstChoice;

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
	    	allInfo.putStringArrayList("summSelections",
	    			selections.getStringArrayList("summSelections"));
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
		tFrameSpinner.setOnItemSelectedListener(this);
		
<<<<<<< HEAD
		/**ArrayList<String> values = new ArrayList<String>();
=======
		ArrayList<String> values = new ArrayList<String>();
		values.add("Nothing Selected");
>>>>>>> 274b0f01df3bea56ba36dceb104a108248f0d198
		values.add("Last 7 Days");
		values.add("Last 14 Days");
		values.add("Last 30 Days");
		*/
		
		String[] values = new String[] { "Last 7 Days", "Last 14 Days",
				"Last 30 Days"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, values);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tFrameSpinner.setAdapter(adapter);
		Log.i("SummTFrame", "Bitch is running correctly");		
<<<<<<< HEAD
		
		//tFrameSpinner.setSelected(false);

=======

		tFrameSpinner.setOnItemSelectedListener(this);
>>>>>>> 274b0f01df3bea56ba36dceb104a108248f0d198

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
		firstChoice = (String) parent.getItemAtPosition(pos);
		startDate = Calendar.getInstance();
		endDate = Calendar.getInstance();
		
		
		if(firstChoice == "Nothing Selected") {
			onNothingSelected(parent);
		}
		
		else if(firstChoice == "Last 7 Days"){
			endDate.add(Calendar.DAY_OF_MONTH, -7);
			Log.i("SummTFrame", "You selected something motha titmonger!!7");
		}
		else if(firstChoice == "Last 14 Days"){
			endDate.add(Calendar.DAY_OF_MONTH, -14);
			Log.i("SummTFrame", "You selected something motha titmonger!!14");
		}
		else if(firstChoice == "Last 30 Days"){
			endDate.add(Calendar.DAY_OF_MONTH, -30);
			Log.i("SummTFrame", "You selected something motha titmonger!!30");
		}
		
		//allInfo.putSerializable("start", startDate);
		//allInfo.putSerializable("end", endDate);
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		startDate = Calendar.getInstance();
		endDate = Calendar.getInstance();
	}
	
	public void toNextScreen(View view){
		DatePicker start = (DatePicker) findViewById(R.id.startDate);
		DatePicker end = (DatePicker) findViewById(R.id.endDate);
		Log.i("SummTFrame", "created just the datepickers");
		custStart = Calendar.getInstance();
		custEnd = Calendar.getInstance();
		
		custStart.set(start.getYear(), start.getMonth(), start.getDayOfMonth());
		custEnd.set(end.getYear(), end.getMonth(), end.getDayOfMonth());
		Log.i("SummTFrame", "set custom dates");
		if(custStart.compareTo(custEnd) != 0){
			startDate = custStart;
			endDate = custEnd;
		}
		
		Log.i("SummTFrame","button clicked and processing data");
		if((firstChoice != "Nothing Selected" && (custStart.compareTo(custEnd) != 0)) || 
				(firstChoice == "Nothing Selected" && (custStart.compareTo(custEnd) == 0))){
			Toast.makeText(this, "Only select one option", Toast.LENGTH_LONG).show();
		}
		
		else if(custStart.compareTo(custEnd) > 0){
			Toast.makeText(this, "The end date must be after the start date",
					Toast.LENGTH_LONG).show();
		}
		else{
			allInfo.putSerializable("start", startDate);
			allInfo.putSerializable("end", endDate);
			Intent toLastScreen = new Intent(this, MainSummActivity.class);
			toLastScreen.putExtras(allInfo);
			startActivity(toLastScreen);
		}
	}
	
	/**
	public void resetDatePickers(View view){
		DatePicker start = (DatePicker) findViewById(R.id.startDate);
		DatePicker end = (DatePicker) findViewById(R.id.endDate);
		Log.i("SummTFrame", "about to reset date picker");
		start.updateDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 
				Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		end.updateDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 
				Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		Log.i("SummTFrame", "reset the picker!");
	}
	*/
}
