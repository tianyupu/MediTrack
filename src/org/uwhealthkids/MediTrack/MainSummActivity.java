package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainSummActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_summ);
		
		Intent prevIntent = getIntent();
		Bundle allInfo = prevIntent.getExtras();
		ArrayList<String> selections = new ArrayList<String>();
		
		selections = allInfo.getStringArrayList("summSelections");
		
		Calendar start = (Calendar) allInfo.getSerializable("start");
		Calendar end = (Calendar) allInfo.getSerializable("end");
		
		Integer[] dates = new Integer[] {start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE),
				end.get(Calendar.YEAR), end.get(Calendar.MONTH), end.get(Calendar.DATE)};
		
		//Parsing Stuff starts below
		ParseObject baby = CustomApplication.getInstance().getCurrBaby();
		ParseQuery query = new ParseQuery("Record");
		query.whereEqualTo("baby", baby.getObjectId());
		query.findInBackground(new FindCallback() {
			public void done(List<ParseObject> babyList, ParseException e){
				
			}
			
		});
		
		
		/**
		ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, dates);
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, selections);
		
		Log.i("MainSumm", "Made the adapter but didn't set");
		ListView lv = (ListView) findViewById(R.id.mainList);
		lv.setAdapter(adapter);
		ListView dv = (ListView) findViewById(R.id.secondList);
		dv.setAdapter(adapter2);
		Log.i("MainSumm", "Set the adapter");
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_summ, menu);
		return true;
	}

}
