package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainSummActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final long startTime = System.nanoTime();
		setContentView(R.layout.activity_main_summ);
		Log.i("MainSumm", "set content passed");
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		Log.i("MainSumm", "Parse initialized");

		Intent prevIntent = getIntent();
		Bundle allInfo = prevIntent.getExtras();
		ArrayList<String> selections = new ArrayList<String>();
		final ArrayList<String> summary = new ArrayList<String>();
		final Bundle thingsToSummarize = new Bundle();

		selections = allInfo.getStringArrayList("summSelections");
		//Log.i("MainSumm", selections.get(0));

		Calendar start = (Calendar) allInfo.getSerializable("start");
		Calendar end = (Calendar) allInfo.getSerializable("end");
		Log.i("MainSumm", "got past calendar declarations");
		
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.mainSummLayout);
		final TextView title = (TextView) findViewById(R.id.mainSummTitle);
		title.setText("Summary for: " + (start.get(Calendar.MONTH) + 1) + "/" +
				start.get(Calendar.DATE) + "/" + start.get(Calendar.YEAR) + 
				" to " + (end.get(Calendar.MONTH) + 1) + "/" + end.get(Calendar.DATE)
				+ "/" + end.get(Calendar.YEAR));
		Log.i("MainSumm", "Summary for: " + start.get(Calendar.MONTH) + "/" +
				start.get(Calendar.DATE) + "/" + start.get(Calendar.YEAR) + 
				" to " + end.get(Calendar.MONTH) + "/" + end.get(Calendar.DATE)
				+ "/" + end.get(Calendar.YEAR));

        /**final RelativeLayout.LayoutParams params = 
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                                                RelativeLayout.LayoutParams.WRAP_CONTENT);


            params.addRule(RelativeLayout.ABOVE, R.id.mainList);
            title.setLayoutParams(params);
    		Log.i("MainSumm", "crashing after setlayout");
            layout.addView(title, params);
		*/
		//TextView title = (TextView) findViewById(R.id.mainSummTitle);
		Log.i("MainSumm", "created title textview");
		//setContentView(R.layout.activity_main_summ);
		
		final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, summary);
		Log.i("MainSumm", "Made the adapter but didn't set");
		ListView lv = (ListView) findViewById(R.id.mainList);
		lv.setAdapter(adapter);
		
		


		//Integer[] dates = new Integer[] {start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE),
		//		end.get(Calendar.YEAR), end.get(Calendar.MONTH), end.get(Calendar.DATE)};

		//Parsing Stuff starts below		

		ParseObject baby = CustomApplication.getInstance().getCurrBaby();
		ParseQuery query = new ParseQuery("Record");
		Log.i("MainSumm", "record query set");
		query.whereEqualTo("baby", baby);
		
		//Filter out list to only include selections made on first page
		ArrayList<ParseObject> constraints = new ArrayList<ParseObject>();
		for(int i = 0; i < selections.size(); i++){
			String choice = selections.get(i);
			//Log.i("MainSumm", choice);
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
			else if(choice.equals("Feeding Goal")) {
				try {
					charObject = charquery.get(AddActivity.CHAR_ID_FEEDING);
				} catch (ParseException e1) {
					Log.d("MainSumm", "could not find char");
				}
				constraints.add(charObject);
				//query.whereEqualTo("charact", charObject);
				Log.i("MainSumm", "equal to feeding goal");
			}
		}
		//apply constraints
		query.whereContainedIn("charact", constraints);
		query.whereGreaterThanOrEqualTo("updatedAt", start.getTime());
		query.orderByAscending("updatedAt");
		Log.i("MainSumm", "past constraints");
		
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		Log.i("MainSumm", "past cache");
		//make list of the parse objects from record




		query.findInBackground(new FindCallback() {
			public void done(List<ParseObject> babyList, ParseException e){


				Log.i("MainSumm", "entered findinbackground done method");
				
				ArrayList<Double> pulseArray = new ArrayList<Double>();
				ArrayList<Integer> heartRateArray = new ArrayList<Integer>();
				ArrayList<int[]> bpArray = new ArrayList<int[]>();
				boolean firstWeightAdded = false;
				double[] weights = new double[2];
				
				
				for(int i = 0; i < babyList.size(); i++){
					ParseObject charact = babyList.get(i).getParseObject("charact");
					Calendar updated = Calendar.getInstance();
					updated.setTime(babyList.get(i).getUpdatedAt());
							//(Date) babyList.get(i).getUpdatedAt();
					double value1 = babyList.get(i).getDouble("value1");
					double value2 = babyList.get(i).getDouble("value2");
					String objID = charact.getObjectId();
					Log.i("MainSumm", babyList.get(i).getObjectId() + " " + 
							babyList.get(i).getDouble("value1") + " " +
							objID);
					
					if(objID.equals(AddActivity.CHAR_ID_WEIGHT)){
						Log.i("MainSumm", "made it into weight if");
						if(!firstWeightAdded){
							weights[0] = value1;
							firstWeightAdded = true;
						}
						else if(firstWeightAdded){
							weights[1] = value1;
							Log.i("MainSumm", "adding weight to summarize");
						}
						
					}
					else if(objID.equals(AddActivity.CHAR_ID_HEARTRATE)){
						heartRateArray.add((int)value1);
						//thingsToSummarize.putInt("heartrate", (int)value1);
					}
					else if(objID.equals(AddActivity.CHAR_ID_PULSEOXYGEN)){
						pulseArray.add(value1);
						//thingsToSummarize.putDouble("pulseoxygen", value1);
					}
					else if(objID.equals(AddActivity.CHAR_ID_BLOODPRESSURE)){
						int[] bp = new int[] {(int) value1, (int) value2};
						bpArray.add(bp);
						//thingsToSummarize.putIntArray("bloodpressure", bp);
					}
					else if(objID.equals(AddActivity.CHAR_ID_FEEDING)){
						thingsToSummarize.putInt("feeding", (int) value1);
					}
					
					
					
					/**
					charact.fetchIfNeededInBackground(new GetCallback() {
						public void done(ParseObject object, ParseException e) {
							Log.i("MainSumm", object.getString("name"));
						}
					});

					babyList.get(i).fetchIfNeededInBackground(new GetCallback() {
						public void done(ParseObject object, ParseException e) {
							//Log.i("MainSumm", object.toString() + " hey");
						}
					});
					*/
				} //end of for
				Log.i("MainSumm", "made it out of for");
				String summaryString;
				if(weights[0] != 0.0 && weights[1] != 0.0){
					Log.i("MainSumm", "inside weight if");
					summaryString = "Baby gained " + 
							(weights[1] - weights[0]);
					Log.i("MainSumm", summaryString);
					summary.add(summaryString);
				}
				else if(weights[0] != 0.0){
					summaryString = "Baby gained 0";
					Log.i("MainSumm", summaryString);
					summary.add(summaryString);
				}

				if(!(heartRateArray.isEmpty())){
					int sum = 0;
					for(int i = 0; i < heartRateArray.size(); i++){
						sum += heartRateArray.get(i);
					}
					double average = sum / heartRateArray.size();
					summaryString = "Average heart rate is " + average;
					summary.add(summaryString);
				}
				
				if(!(pulseArray.isEmpty())) {
					double sum = 0;
					for(int i = 0; i < pulseArray.size(); i++){
						sum += pulseArray.get(i);
					}
					double average = sum / pulseArray.size();
					summaryString = "Average pulse oxygen saturation is " + average;
					summary.add(summaryString);
				}
				if(!(bpArray.isEmpty())) {
					int sum1 = 0;
					int sum2 = 0;
					for(int i = 0; i < bpArray.size(); i++){
						sum1 += bpArray.get(i)[0];
						sum2 += bpArray.get(i)[1];
					}
					double average1 = sum1 / bpArray.size();
					double average2 = sum2 / bpArray.size();
					summaryString = "Average blood pressure is " + average2 + 
							"/" + average1;
					summary.add(summaryString);
				}
				if(thingsToSummarize.containsKey("feeding")){
					SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(CustomApplication.getInstance());
					String goal = sharedPrefs.getString("pref_feed_goal", "not set");
					if(goal.equals("not set")){
						summaryString = "No feeding goal set. Feeding is at " +
								thingsToSummarize.getInt("feeding");
					}
					else{
						summaryString = "Your goal is " + goal + 
								" and your feeding is at " + 
								thingsToSummarize.getInt("feeding");
					}
					summary.add(summaryString);
				}
				Log.i("MainSumm", summary.toString());
				adapter.notifyDataSetChanged();
				long endTime = System.nanoTime();
				Log.i("MainSumm", "It took " + 	(endTime - startTime));
			}

		});


		/**
		ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, dates);


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
