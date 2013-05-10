package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("SummTFrame","Good for you, you created it");
		setContentView(R.layout.activity_summ_tframe);
		Log.i("SummTFrame", "Past setContentView!!");
		//----------------------------------------------------------
		//Used to test if information is passed correctly with intent
		// Get the message from the intent
		
    	DatePicker datePickerFirst = (DatePicker) findViewById(R.id.startDate);
    	DatePicker datePickerLast = (DatePicker) findViewById(R.id.endDate);	
    	Log.i("SummTFrame", "got date pickers");
    	//check if user is using a phone or a tablet
        DisplayMetrics displayMetrics = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels / displayMetrics.densityDpi;
        int height = displayMetrics.heightPixels / displayMetrics.densityDpi;
        Log.i("SummTFrame", "calculated width and height");
        double screenDiagonal = Math.sqrt( width * width + height * height );
    	
    	if (screenDiagonal <= 4.8) {
    		datePickerFirst.setCalendarViewShown(false);
    		datePickerLast.setCalendarViewShown(false);
    		Log.i("SummTFrame", "set calendar view to not show up");
    	}
		
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
		


		ArrayList<String> values = new ArrayList<String>();
		values.add("Nothing Selected");
		values.add("Last 7 Days");
		values.add("Last 14 Days");
		values.add("Last 30 Days");
		
		//String[] values = new String[] { "Last 7 Days", "Last 14 Days",
		//		"Last 30 Days"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, values);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tFrameSpinner.setAdapter(adapter);
		Log.i("SummTFrame", "Bitch is running correctly");		
		
		//tFrameSpinner.setSelected(false);


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
		Log.i("SummTFrame", firstChoice);
		startDate = Calendar.getInstance();
		endDate = Calendar.getInstance();
		
		
		if(firstChoice == "Nothing Selected") {
			//onNothingSelected(parent);
			Log.i("SummTFrame", firstChoice);
		}
		
		else if(firstChoice == "Last 7 Days"){
			startDate.add(Calendar.DAY_OF_MONTH, -7);
			Log.i("SummTFrame", "You selected something motha titmonger!!7");
		}
		else if(firstChoice == "Last 14 Days"){
			startDate.add(Calendar.DAY_OF_MONTH, -14);
			Log.i("SummTFrame", "You selected something motha titmonger!!14");
		}
		else if(firstChoice == "Last 30 Days"){
			startDate.add(Calendar.DAY_OF_MONTH, -30);
			Log.i("SummTFrame", "You selected something motha titmonger!!30");
		}
		
		//allInfo.putSerializable("start", startDate);
		//allInfo.putSerializable("end", endDate);
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		//startDate = Calendar.getInstance();
		//endDate = Calendar.getInstance();
	}
	
	public void toNextScreen(View view){
		DatePicker start = (DatePicker) findViewById(R.id.startDate);
		DatePicker end = (DatePicker) findViewById(R.id.endDate);
		Log.i("SummTFrame", "created just the datepickers");
		custStart = Calendar.getInstance();
		custEnd = Calendar.getInstance();
		
		Spinner TFspin = (Spinner) findViewById(R.id.pick_tframe);
		firstChoice = (String) TFspin.getSelectedItem();
		
		custStart.set(start.getYear(), start.getMonth(), start.getDayOfMonth());
		custEnd.set(end.getYear(), end.getMonth(), end.getDayOfMonth());
		Log.i("SummTFrame", "set custom dates");
		//custStart.compareTo(custEnd) != 0
		boolean custDateSelected = false;
		if((start.getYear() != end.getYear()) || (start.getMonth() != end.getMonth())
				|| (start.getDayOfMonth() != end.getDayOfMonth())){
			custDateSelected = true;
			Log.i("SummTFrame", "set cust dates to reg dates");
		}
		
		Log.i("SummTFrame","button clicked and processing data");
		//(firstChoice != "Nothing Selected" && (custStart.compareTo(custEnd) != 0)) || 
		//(firstChoice == "Nothing Selected" && (custStart.compareTo(custEnd) == 0))
		if((firstChoice != "Nothing Selected" && (custDateSelected)) || 
				(firstChoice == "Nothing Selected" && !(custDateSelected))){
			Toast.makeText(this, "Only select one option", Toast.LENGTH_SHORT).show();
		}
		
		else if(custStart.compareTo(custEnd) > 0){
			Toast.makeText(this, "The end date must be after the start date",
					Toast.LENGTH_SHORT).show();
		}
		else if(firstChoice == "Nothing Selected" && (custDateSelected)){
			startDate = custStart;
			endDate = custEnd;
			allInfo.putSerializable("start", startDate);
			allInfo.putSerializable("end", endDate);
			Intent toLastScreen = new Intent(this, MainSummActivity.class);
			toLastScreen.putExtras(allInfo);
			startActivity(toLastScreen);
			finish();
		}
		else{
			allInfo.putSerializable("start", startDate);
			allInfo.putSerializable("end", endDate);
			Intent toLastScreen = new Intent(this, MainSummActivity.class);
			toLastScreen.putExtras(allInfo);
			startActivity(toLastScreen);
			finish();
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
