package org.uwhealthkids.MediTrack;

import com.parse.Parse;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class AddActivity extends Activity {
	protected Integer[] layouts = {
			R.layout.add_heartrate, R.layout.add_bloodpressure,
			R.layout.add_pulseoxygen, R.layout.add_feedings,
			R.layout.add_weight, R.layout.add_medication
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		setContentView(R.layout.activity_add);
		
		GridView gridView = (GridView) findViewById(R.id.addGridView);
		gridView.setAdapter(new ImageAdapter(this));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i;
				if (layouts[arg2] == R.layout.add_pulseoxygen) {
					i = new Intent(AddActivity.this, SeekBarAddCharActivity.class);
				}
				else if (layouts[arg2] == R.layout.add_medication) {
					i = new Intent(AddActivity.this, MedicationAddActivity.class);
				}
				else {
					i = new Intent(AddActivity.this, DefaultAddCharActivity.class);
				}
				i.putExtra("charId", arg2);
				startActivity(i);
			}
		});
	}
	
	public class ImageAdapter extends BaseAdapter {
		private Context mContext;
		
		public ImageAdapter(Context c) {
			mContext = c;
		}
		
		public int getCount() {
			return mThumbIds.length;
		}
		
		public Object getItem(int position) {
			return mThumbIds[position];
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ImageView imageView;
			if (arg1 == null) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				//imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) arg1;
			}
			
			imageView.setImageResource(mThumbIds[arg0]);
			return imageView;
		}
		
		private Integer[] mThumbIds = {
				R.drawable.char0_heartrate, R.drawable.char1_bloodpressure,
				R.drawable.char2_pulseoxygen, R.drawable.char3_feedings,
				R.drawable.char4_weight, R.drawable.char5_medication
		};
	}

}
