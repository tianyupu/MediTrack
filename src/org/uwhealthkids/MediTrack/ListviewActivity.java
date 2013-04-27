package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.parse.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListviewActivity extends Activity {

	private ListView listView;
	private ListAdapter listAdapter;
	private ArrayList<RecordDetailsPojo> pojoArrayList;
	private ArrayList<String> stringsList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
    
        //recordList = new ArrayList<ParseObject>();
        listView = (ListView) findViewById(R.id.listView);
        pojoArrayList = new ArrayList<RecordDetailsPojo>();
        stringsList = getIntent().getStringArrayListExtra("stringsList");

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringsList);
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
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());
        listView.setAdapter(listAdapter);
	}    
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}
}
