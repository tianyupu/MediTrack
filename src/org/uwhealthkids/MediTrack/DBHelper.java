package org.uwhealthkids.MediTrack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

	// Database attributes
	public static final String DB_NAME = "meditrack_database";
	public static final int DB_VERSION = 1;

	// Table attributes
	public static final String TABLE_NAME_DOCTOR = "doctor_table";
	public static final String COLUMN_NAME_FIRSTNAME = "firstname_column";
	public static final String COLUMN_NAME_LASTNAME = "lastname_column";
	public static final String COLUMN_NAME_PHONE = "phone_column";
	public static final String COLUMN_NAME_EMAIL = "email_column";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sqlQueryCreateDoctorTable = 
				"create table if not exists " + TABLE_NAME_DOCTOR + " ( " + BaseColumns._ID + 
				" integer primary key autoincrement, " + COLUMN_NAME_FIRSTNAME + 
				" text not null, " + COLUMN_NAME_LASTNAME + " text not null, " 
				+ COLUMN_NAME_PHONE + " text not null" + COLUMN_NAME_EMAIL +
				" text not null)";
		// Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
		db.execSQL(sqlQueryCreateDoctorTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
