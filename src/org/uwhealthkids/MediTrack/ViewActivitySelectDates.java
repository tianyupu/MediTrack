package org.uwhealthkids.MediTrack;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;

public class ViewActivitySelectDates extends Activity {

	private DatePicker datePickerFirst;
	private DatePicker datePickerLast;
	
	private String charid;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_selectdates);
		
		charid = (String) this.getIntent().getExtras().get("charid");
		
		if (charid == "VxapkUMVwy" || charid == "2Ibm6va7I7") {
			findViewById(R.id.button_graph).setEnabled(false);
		}
		
    	datePickerFirst = (DatePicker) findViewById(R.id.datePicker_first);
    	datePickerLast = (DatePicker) findViewById(R.id.datePicker_last);	
    	
    	//check if user is using a phone or a tablet
        DisplayMetrics displayMetrics = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels / displayMetrics.densityDpi;
        int height = displayMetrics.heightPixels / displayMetrics.densityDpi;

        double screenDiagonal = Math.sqrt( width * width + height * height );
    	
    	if (screenDiagonal >= 7.0) {
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
		intent.putExtra("charid", charid);
		
		//ArrayList<Calendar> calArr = new ArrayList<Calendar>();
		//ArrayList<Integer> valOne = new ArrayList<Integer>();
		//ArrayList<Integer> valTwo = new ArrayList<Integer>();
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
		intent.putExtra("charid", charid);
		
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
