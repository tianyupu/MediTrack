package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListviewActivity extends Activity {

	private ListView listView;
	private ListAdapter listAdapter;
	private ArrayList<RecordDetailsPojo> pojoArrayList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
    
        listView = (ListView) findViewById(R.id.listView);
        pojoArrayList = new ArrayList<RecordDetailsPojo>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());
        listView.setAdapter(listAdapter);
    }

	// To create a List that contains undergraduate names, we have to read the SQLite database
	//We are going to do it in the separate method
	public List<String> populateList(){

		List<String> stringsList = new ArrayList<String>();
		DBHelper openHelperClass = new DBHelper(this);
		SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();

		Cursor cursor = sqliteDatabase.query(DBHelper.TABLE_NAME_RECORD, null, null, null, null, null, null);
		startManagingCursor(cursor);
		String temp;
		
		while (cursor.moveToNext()) {

			Calendar cal = null;
			cal.setTime(new Date(cursor.getString(cursor.getColumnIndex
					(DBHelper.RECORD_COLUMN_NAME_TIME))));
			int valueOne = cursor.getInt(cursor.getColumnIndex
					(DBHelper.RECORD_COLUMN_NAME_VALUEONE));
			int valueTwo = cursor.getInt(cursor.getColumnIndex
					(DBHelper.RECORD_COLUMN_NAME_VALUETWO));

			//pass data to the POJO
			RecordDetailsPojo RecordPojoClass = new RecordDetailsPojo();
			RecordPojoClass.setTime(cal);
			RecordPojoClass.setValOne(valueOne);
			RecordPojoClass.setValTwo(valueTwo);
			pojoArrayList.add(RecordPojoClass);

			//List of strings to display in the listview
			temp = cal.MONTH + "/" + cal.DATE + "   " + valueOne + " " + valueTwo;
			stringsList.add(temp);
		}

		sqliteDatabase.close();

		return stringsList;
	}

	@Override
	protected void onResume() {
		super.onResume();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());
        listView.setAdapter(listAdapter);
	}    
}
