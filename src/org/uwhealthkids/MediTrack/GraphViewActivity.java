package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class GraphViewActivity extends Activity {

	private ArrayList<RecordDetailsPojo> pojoArrayList;
	private List<Integer> valuesOneArr;
	private List<Integer> valuesTwoArr;
	private List<Calendar> calendarArray;
	
	private int charid;
	private int babyid;
	
	private Calendar dateFirst;
	private Calendar dateLast;
	
	private DBHelper dbHelper;
	private SQLiteDatabase sqliteDatabase;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graphview);
		
		Intent intent = getIntent();
		dateLast.set(intent.getIntExtra("lastYear", 0), intent.getIntExtra("lastMonth", 0), 
				intent.getIntExtra("lastDay", 0));
		dateFirst.set(intent.getIntExtra("firstYear", 0), intent.getIntExtra("firstMonth", 0), 
				intent.getIntExtra("firstDay", 0));
		charid = intent.getIntExtra("char_id", 0);
		babyid = intent.getIntExtra("baby_id", 0);
		String selection = DBHelper.RECORD_COLUMN_NAME_CHAR + "='" + charid + "' AND " +
				DBHelper.RECORD_COLUMN_NAME_BABY + "='" + babyid + "'";
		String[] columns = {DBHelper.RECORD_COLUMN_NAME_TIME, DBHelper.RECORD_COLUMN_NAME_VALUEONE, 
				DBHelper.RECORD_COLUMN_NAME_VALUETWO};

		sqliteDatabase = dbHelper.getReadableDatabase();
		
		Cursor cursor = sqliteDatabase.query(DBHelper.TABLE_NAME_RECORD, 
				columns, selection, null, null, null, DBHelper.RECORD_COLUMN_NAME_TIME);
		
		while(cursor.moveToNext()) {
			Calendar cal = null;
			cal.setTime(new Date(cursor.getString(cursor.getColumnIndex
					(DBHelper.RECORD_COLUMN_NAME_TIME))));
			int valOne = Integer.parseInt(cursor.getString(cursor.getColumnIndex
					(DBHelper.RECORD_COLUMN_NAME_VALUEONE)));
			int valTwo = Integer.parseInt(cursor.getString(cursor.getColumnIndex
					(DBHelper.RECORD_COLUMN_NAME_VALUETWO)));

			RecordDetailsPojo pojoClass = new RecordDetailsPojo();
			pojoClass.setTime(cal);
			pojoClass.setValOne(valOne);
			pojoClass.setValTwo(valTwo);

			pojoArrayList.add(pojoClass);

			valuesOneArr.add(valOne);
			valuesTwoArr.add(valTwo);
			calendarArray.add(cal);
		}
	}
	
}
