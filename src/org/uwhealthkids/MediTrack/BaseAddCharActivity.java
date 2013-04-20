package org.uwhealthkids.MediTrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BaseAddCharActivity extends Activity {
	private Integer[] layouts = {
			R.layout.add_heartrate, R.layout.add_bloodpressure,
			R.layout.add_pulseoxygen, R.layout.add_feedings,
			R.layout.add_weight, R.layout.add_medication
	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		int id = i.getExtras().getInt("charId");
		setContentView(layouts[id]);
	}
}
