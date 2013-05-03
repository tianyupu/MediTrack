package org.uwhealthkids.MediTrack;

import com.parse.Parse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DefaultAddCharActivity extends AddActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		
		setContentView(layouts[id]);
	}
	
	// Called when the user touches the save button
	public void saveChar(View view) {
		
	}
}