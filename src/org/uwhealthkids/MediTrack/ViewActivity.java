package org.uwhealthkids.MediTrack;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;

public class ViewActivity extends Activity {

	private Button graph;
	private DatePicker datePickerFirst;
	private DatePicker datePickerLast;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		
		graph = (Button) findViewById(R.id.button_graph);	
    	datePickerFirst = (DatePicker) findViewById(R.id.datePicker_first);
    	datePickerLast = (DatePicker) findViewById(R.id.datePicker_last);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}
	
	public void onGraphButtonClicked(View v) {		
		Intent intent = new Intent(this, GraphViewActivity.class);
		intent.putExtra("lastYear", datePickerLast.getYear());
		intent.putExtra("lastMonth", datePickerLast.getMonth());
		intent.putExtra("lastDay", datePickerLast.getDayOfMonth());
		intent.putExtra("firstYear", datePickerFirst.getYear());
		intent.putExtra("firstMonth", datePickerFirst.getMonth());
		intent.putExtra("firstDay", datePickerFirst.getDayOfMonth());
		
		startActivity(intent);
	}

}
