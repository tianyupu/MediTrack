package org.uwhealthkids.MediTrack;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MedicationAddActivity extends AddActivity {
	public void onCreate(Bundle savedBundleInstance) {
		super.onCreate(savedBundleInstance);
		
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		
		setContentView(super.layouts[id]);
		
		Spinner spinner = (Spinner) findViewById(R.id.medication_spinner);
		DBHelper dbHelper = new DBHelper(this);
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cur = db.rawQuery("SELECT "+DBHelper.MEDICATION_COLUMN_NAME_NAME+"AS name FROM "+DBHelper.TABLE_NAME_MEDICATION, null);
		ArrayList<String> medicationNames = new ArrayList<String>();
		int index = cur.getColumnIndex("name");
		if (cur.moveToFirst()) {
			medicationNames.add(cur.getString(index));
			while (cur.moveToNext()) {
				medicationNames.add(cur.getString(index));
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(spinner.getContext(), super.layouts[id], medicationNames);
		spinner.setAdapter(adapter);
	}
	
}
