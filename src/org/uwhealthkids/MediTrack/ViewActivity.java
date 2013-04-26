package org.uwhealthkids.MediTrack;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ViewActivity extends Activity {

	private DatePicker datePickerFirst;
	private DatePicker datePickerLast;

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		
    	datePickerFirst = (DatePicker) findViewById(R.id.datePicker_first);
    	datePickerLast = (DatePicker) findViewById(R.id.datePicker_last);	
    	
    	DisplayMetrics metrics = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(metrics);
    	int width = metrics.widthPixels;
    	int height = metrics.heightPixels;
    	
    	if (width > 500 && height > 900) {
    		datePickerFirst.setCalendarViewShown(false);
    		datePickerLast.setCalendarViewShown(false);
    	}
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
	
	public void onListButtonClicked(View v) {		
		Intent intent = new Intent(this, ListviewActivity.class);
		intent.putExtra("lastYear", datePickerLast.getYear());
		intent.putExtra("lastMonth", datePickerLast.getMonth());
		intent.putExtra("lastDay", datePickerLast.getDayOfMonth());
		intent.putExtra("firstYear", datePickerFirst.getYear());
		intent.putExtra("firstMonth", datePickerFirst.getMonth());
		intent.putExtra("firstDay", datePickerFirst.getDayOfMonth());
		
		startActivity(intent);
	}
}
