package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.parse.*;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListviewActivity extends Activity {

	private ListView listView;
	private ArrayAdapter<String> listAdapter;
	private List<String> stringsList;
	private String units;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        
        stringsList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringsList);

		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		ParseQuery query = new ParseQuery("Record");
		ParseQuery babyquery = new ParseQuery("Baby");
		ParseObject babyObject = null;
		try {
			babyObject = babyquery.get(CustomApplication.getInstance().getCurrBaby().getObjectId().toString());
		} catch (ParseException e1) {
			Log.d("tag", "could not find baby");
		}
		ParseQuery charquery = new ParseQuery("Charact");
		ParseObject charObject = null;
		try {
			charObject = charquery.get(this.getIntent().getExtras().getString("charid"));
		} catch (ParseException e1) {
			Log.d("tag", "could not find char");
		}
		units = charObject.getString("Unit");
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.whereEqualTo( "baby", babyObject);
		query.whereEqualTo( "charact" , charObject);
		query.orderByAscending("time");
		query.findInBackground(new FindCallback() {
		    @SuppressWarnings("deprecation")
			public void done(List<ParseObject>tempList, ParseException e) {
		        if (e == null) {
		        	Iterator<ParseObject> iter = tempList.iterator();
		        	while(iter.hasNext()) {
		        		ParseObject po = (ParseObject) iter.next();
		        		String temp = "";
		        		if (po.getNumber("value1") != null) {
		        			temp = temp + po.getNumber("value1").toString();
		        		}
		        		if (po.getNumber("value2") != null) {
		        			temp = temp + "/" + po.getNumber("value2").toString();
		        		}
		        		else {
		        			temp = temp + units;
		        		}
		        		if (po.getDate("time") != null) {
		        			temp = temp + " " + (po.getDate("time").getMonth()+1) + "/" + 
			        				po.getDate("time").getDate();
		        		}
		        		
		        		//po.
		        		stringsList.add(temp);
		        	}
		        	listAdapter.notifyDataSetChanged();
		        } else {
		            //do nothing
		        }
		    }
		});
        
        //recordList = new ArrayList<ParseObject>();
        listView = (ListView) findViewById(R.id.listView);
        //pojoArrayList = new ArrayList<RecordDetailsPojo>();
        //stringsList = getIntent().getStringArrayListExtra("stringsList");
        
        listView.setAdapter(listAdapter);
        //babyId = getIntent().getStringExtra("" /*need babyId passed from other method*/);
        //charId = getIntent().getStringExtra("" /*charId passed from last method*/);
    }

	public List<String> populateList(){

//		List<String> stringsList = new ArrayList<String>();
//		DBHelper openHelperClass = new DBHelper(this);
//		SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
//
//		Cursor cursor = sqliteDatabase.query(DBHelper.TABLE_NAME_RECORD, null, null, null, null, null, null);
//		startManagingCursor(cursor);
//		String temp;
//		
//		while (cursor.moveToNext()) {
//
//			Calendar cal = null;
//			cal.setTime(new Date(cursor.getString(cursor.getColumnIndex
//					(DBHelper.RECORD_COLUMN_NAME_TIME))));
//			int valueOne = cursor.getInt(cursor.getColumnIndex
//					(DBHelper.RECORD_COLUMN_NAME_VALUEONE));
//			int valueTwo = cursor.getInt(cursor.getColumnIndex
//					(DBHelper.RECORD_COLUMN_NAME_VALUETWO));
//
//			//pass data to the POJO
//			RecordDetailsPojo RecordPojoClass = new RecordDetailsPojo();
//			RecordPojoClass.setTime(cal);
//			RecordPojoClass.setValOne(valueOne);
//			RecordPojoClass.setValTwo(valueTwo);
//			pojoArrayList.add(RecordPojoClass);
//
//			//List of strings to display in the listview
//			temp = cal.MONTH + "/" + cal.DATE + "   " + valueOne + " " + valueTwo;
//			stringsList.add(temp);
//		}
//
//		sqliteDatabase.close();
		
		return null;
	}

	@Override
	protected void onResume() {
		super.onResume();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringsList);
        listView.setAdapter(listAdapter);
	}    
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}
}
