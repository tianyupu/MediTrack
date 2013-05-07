package org.uwhealthkids.MediTrack;

import org.uwhealthkids.MediTrack.AddActivity.ImageAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;



public class DoctorMainActivity extends Activity {
	private  SQLiteDatabase sqliteDatabase;
	private DBHelper dbHelper;
	private final int doctorID = 1; 
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_main);
		
		final ListView listview = (ListView) findViewById(R.id.list);
		
		sqliteDatabase = dbHelper.getReadableDatabase();


	}
	
	
	
	
}

	


