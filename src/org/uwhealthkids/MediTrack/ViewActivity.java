package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
	
	private String babyId;
	private String charId;
	
	private FindCallback f;
	

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		
        babyId = getIntent().getStringExtra("" /*need babyId passed from other method*/);
        charId = getIntent().getStringExtra("" /*charId passed from last method*/);
		
    	datePickerFirst = (DatePicker) findViewById(R.id.datePicker_first);
    	datePickerLast = (DatePicker) findViewById(R.id.datePicker_last);	
    	
    	//recordList = new ArrayList<ParseObject>();
    	
    	DisplayMetrics metrics = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(metrics);
    	int width = metrics.widthPixels;
    	int height = metrics.heightPixels;
    	
    	if (width < 500 && height < 900) {
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
		
		ArrayList<Calendar> calArr = new ArrayList<Calendar>();
		ArrayList<Integer> valOne = new ArrayList<Integer>();
		ArrayList<Integer> valTwo = new ArrayList<Integer>();
		//Iterator<ParseObject> iter = recordList.iterator();
		//while(iter.hasNext()) {
			//ParseObject p = iter.next();
			//valOne.add(p.getInt("valueOne"));
			//valTwo.add(p.getInt("valueTwo"));
			//Calendar cal = Calendar.getInstance();
			//cal.setTime(p.getDate("timestamp"));
			//calArr.add(cal);
		//}
		
		//intent.putExtra("valOneList", valOne);
		//intent.putExtra("valTwoList", valTwo);
		//intent.putExtra("dateList", calArr);
		
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
		
		
//		ArrayList<String> stringsList = new ArrayList<String>();
//		Iterator<ParseObject> iter = recordList.iterator();
//		while(iter.hasNext()) {
//			ParseObject p = iter.next();
//			String temp = p.getDate("timestamp").getMonth() + "/" + 
//					p.getDate("timestamp").getDate() + 
//					p.getInt("valueOne") + " " + p.getInt("valueTwo");
//			stringsList.add(temp);
//		}
		
		//intent.putStringArrayListExtra("stringsList", stringsList);
		
		startActivity(intent);
	}
}
