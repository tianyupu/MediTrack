package org.uwhealthkids.MediTrack;

import android.content.Intent;
import android.os.Bundle;

public class BaseAddCharActivity extends AddActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		
		setContentView(layouts[id]);
	}
}