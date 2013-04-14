package org.uwhealthkids.MediTrack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

	// Database attributes
	public static final String DB_NAME = "MediTrack";
	public static final int DB_VERSION = 1;

	// Doctor table attributes
	public static final String TABLE_NAME_DOCTOR = "Doctor";
	public static final String DOCTOR_COLUMN_NAME_FIRSTNAME = "fname";
	public static final String DOCTOR_COLUMN_NAME_LASTNAME = "surname";
	public static final String DOCTOR_COLUMN_NAME_PHONE = "phone";
	public static final String DOCTOR_COLUMN_NAME_EMAIL = "email";
	
	// Baby table attributes
	public static final String TABLE_NAME_BABY = "Baby";
	public static final String BABY_COLUMN_NAME_FIRSTNAME = "fname";
	public static final String BABY_COLUMN_NAME_SURNAME = "surname";
	public static final String BABY_COLUMN_NAME_DOB = "dob";
	public static final String BABY_COLUMN_NAME_DOCTOR = "doctor_id";

	// Characteristic table attributes
	public static final String TABLE_NAME_CHARACTERISTIC = "Charact";
	public static final String CHARACTERISTIC_COLUMN_NAME_NAME = "name";
	
	// Characteristic table attributes
	public static final String TABLE_NAME_MEDICATION = "Medication";
	public static final String MEDICATION_COLUMN_NAME_NAME = "name";
	
	// Baby-Parent Relationship table attributes
	public static final String TABLE_NAME_BABYPARENTREL = "BabyParentRel";
	public static final String BABYPARENTREL_COLUMN_NAME_PARENT = "parent_id";
	public static final String BABYPARENTREL_COLUMN_NAME_BABY = "baby_id";

	// Parent table attributes
	public static final String TABLE_NAME_PARENT = "Parent";
	public static final String PARENT_COLUMN_NAME_FIRSTNAME = "fname";
	public static final String PARENT_COLUMN_NAME_SURNAME = "surname";
	public static final String PARENT_COLUMN_NAME_EMAIL = "email";
	public static final String PARENT_COLUMN_NAME_PHONE = "phone";

	// Record table attributes
	public static final String TABLE_NAME_RECORD = "Record";
	public static final String RECORD_COLUMN_NAME_TIME = "timestamp";
	public static final String RECORD_COLUMN_NAME_BABY = "baby_id";
	public static final String RECORD_COLUMN_NAME_CHAR = "char_id";
	public static final String RECORD_COLUMN_NAME_VALUEONE = "value1";
	public static final String RECORD_COLUMN_NAME_VALUETWO = "value2";
	public static final String RECORD_COLUMN_NAME_MED = "med_id";
	public static final String RECORD_COLUMN_NAME_NOTES = "notes";	
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sqlQueryCreateDoctorTable = 
				"create table if not exists " + TABLE_NAME_DOCTOR + " ( " + BaseColumns._ID + 
				" integer primary key autoincrement, " + DOCTOR_COLUMN_NAME_FIRSTNAME + 
				" varchar(30), " + DOCTOR_COLUMN_NAME_LASTNAME + " varchar(30), " 
				+ DOCTOR_COLUMN_NAME_PHONE + " varchar(30), " + DOCTOR_COLUMN_NAME_EMAIL +
				" varchar(30))";
		db.execSQL(sqlQueryCreateDoctorTable);
		
		String sqlQueryCreateParentTable = 
				"create table if not exists " + TABLE_NAME_PARENT + " ( " + BaseColumns._ID + 
				" integer primary key autoincrement, " + PARENT_COLUMN_NAME_FIRSTNAME + 
				" varchar(30), " + PARENT_COLUMN_NAME_SURNAME + " varchar(30), " 
				+ PARENT_COLUMN_NAME_PHONE + " varchar(30), " + PARENT_COLUMN_NAME_EMAIL +
				" varchar(30))";
		db.execSQL(sqlQueryCreateParentTable);
		
		String sqlQueryCreateBabyTable = 
				"create table if not exists " + TABLE_NAME_BABY + " ( " + BaseColumns._ID + 
				" integer primary key autoincrement, " + BABY_COLUMN_NAME_FIRSTNAME + 
				" varchar(30), " + BABY_COLUMN_NAME_SURNAME + " varchar(30), " 
				+ BABY_COLUMN_NAME_DOB + " date, " + BABY_COLUMN_NAME_DOCTOR +
				" integer FOREIGN KEY (doctor_id) REFERENCES Doctor(id))";
		db.execSQL(sqlQueryCreateBabyTable);
		
		String sqlQueryCreateCharactTable = 
				"create table if not exists " + TABLE_NAME_CHARACTERISTIC + " ( " + BaseColumns._ID + 
				" integer primary key autoincrement, " + CHARACTERISTIC_COLUMN_NAME_NAME + 
				" varchar(30))";
		db.execSQL(sqlQueryCreateCharactTable);
		
		String sqlQueryCreateMedicationTable = 
				"create table if not exists " + TABLE_NAME_MEDICATION + " ( " + BaseColumns._ID + 
				" integer primary key autoincrement, " + MEDICATION_COLUMN_NAME_NAME + 
				" varchar(30))";
		db.execSQL(sqlQueryCreateMedicationTable);
		
		String sqlQueryCreateBabyParentTable = 
				"create table if not exists " + TABLE_NAME_BABYPARENTREL + " ( " + 
				BABYPARENTREL_COLUMN_NAME_PARENT + " integer, " + BABYPARENTREL_COLUMN_NAME_BABY + 
				" integer, PRIMARY KEY (parent_id, baby_id), FOREIGN KEY (parent_id) REFERENCES Parent(id)," +
				"FOREIGN KEY (baby_id) REFERENCES Baby(id))";
		db.execSQL(sqlQueryCreateBabyParentTable);
		
		String sqlQueryCreateRecordTable = 
				"create table if not exists " + TABLE_NAME_RECORD + " ( " + BaseColumns._ID + 
				" integer primary key autoincrement, " + RECORD_COLUMN_NAME_TIME + 
				" datetime, " + RECORD_COLUMN_NAME_BABY + " integer, " + RECORD_COLUMN_NAME_CHAR +
				" integer, " + RECORD_COLUMN_NAME_VALUEONE + " double, " + RECORD_COLUMN_NAME_VALUETWO + 
				" double, " + RECORD_COLUMN_NAME_MED + " integer, " + RECORD_COLUMN_NAME_NOTES +
				" text, FOREIGN KEY (baby_id) REFERENCES Baby(id), FOREIGN KEY (char_id) REFERENCES" +
				"Charact(id), FOREIGN KEY (med_id) REFERENCES Medication(id))";
		db.execSQL(sqlQueryCreateRecordTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
