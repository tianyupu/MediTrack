package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainSummActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_summ);
		Log.i("MainSumm", "set content passed");
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		Log.i("MainSumm", "Parse initialized");

		Intent prevIntent = getIntent();
		Bundle allInfo = prevIntent.getExtras();
		ArrayList<String> selections = new ArrayList<String>();

		selections = allInfo.getStringArrayList("summSelections");
		Log.i("MainSumm", selections.get(0));

		Calendar start = (Calendar) allInfo.getSerializable("start");
		Calendar end = (Calendar) allInfo.getSerializable("end");

		Integer[] dates = new Integer[] {start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE),
				end.get(Calendar.YEAR), end.get(Calendar.MONTH), end.get(Calendar.DATE)};

		//Parsing Stuff starts below		

		ParseObject baby = CustomApplication.getInstance().getCurrBaby();
		ParseQuery query = new ParseQuery("Record");
		Log.i("MainSumm", "record query set");
		query.whereEqualTo("baby", baby);
		
		//Filter out list to only include selections made on first page
		ArrayList<ParseObject> constraints = new ArrayList<ParseObject>();
		for(int i = 0; i < selections.size(); i++){
			String choice = selections.get(i);
			Log.i("MainSumm", choice);
			ParseObject charObject = null;
			ParseQuery charquery = new ParseQuery("Charact");
			if(choice.equals("Weight")) {
				Log.i("MainSumm", "got to weight");
				try {
					charObject = charquery.get(AddActivity.CHAR_ID_WEIGHT);
				} catch (ParseException e1) {
					Log.d("MainSumm", "could not find char");
				}
				constraints.add(charObject);
				//query.whereEqualTo("charact", charObject);
				Log.i("MainSumm", "equal to weight");
			}
			else if(choice.equals("Heart Rate")) {
				try {
					charObject = charquery.get(AddActivity.CHAR_ID_HEARTRATE);
				} catch (ParseException e1) {
					Log.d("MainSumm", "could not find char");
				}
				constraints.add(charObject);
				//query.whereEqualTo("charact", charObject);
				Log.i("MainSumm", "equal to heart rate");
			}
			else if(choice.equals("Pulse Oxygen Saturation")) {
				try {
					charObject = charquery.get(AddActivity.CHAR_ID_PULSEOXYGEN);
				} catch (ParseException e1) {
					Log.d("MainSumm", "could not find char");
				}
				constraints.add(charObject);
				//query.whereEqualTo("charact", charObject);
				Log.i("MainSumm", "equal to pulse ox");
			}
			else if(choice.equals("Blood Pressure")) {
				try {
					charObject = charquery.get(AddActivity.CHAR_ID_BLOODPRESSURE);
				} catch (ParseException e1) {
					Log.d("MainSumm", "could not find char");
				}
				constraints.add(charObject);
				//query.whereEqualTo("charact", charObject);
				Log.i("MainSumm", "equal to blood pressure");
			}
		}
		//apply constraints
		query.whereContainedIn("charact", constraints);
		query.orderByAscending("updatedAt");
		
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		//make list of the parse objects from record
		query.findInBackground(new FindCallback() {
			public void done(List<ParseObject> babyList, ParseException e){
				Log.i("MainSumm", "entered findinbackground done method");
				
				for(int i = 0; i < babyList.size(); i++){
					ParseObject charact = babyList.get(i).getParseObject("charact");
					Log.i("MainSumm", babyList.get(i).getObjectId() + " " + 
							babyList.get(i).getDouble("value1") + " " +
							charact.getObjectId());
					charact.fetchIfNeededInBackground(new GetCallback() {
						public void done(ParseObject object, ParseException e) {
							Log.i("MainSumm", object.getString("name"));
						}
					});
					/**
					babyList.get(i).fetchIfNeededInBackground(new GetCallback() {
						public void done(ParseObject object, ParseException e) {
							//Log.i("MainSumm", object.toString() + " hey");
						}
					});
					*/
				}
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
