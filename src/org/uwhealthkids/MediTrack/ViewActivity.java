package org.uwhealthkids.MediTrack;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ViewActivity extends Activity {
	protected static Integer[] layouts = {
		R.layout.add_heartrate, R.layout.add_bloodpressure,
		R.layout.add_pulseoxygen, R.layout.add_feedings,
		R.layout.add_weight, R.layout.add_medication
	};
	public final static String CHAR_ID_MEDICATION = "VxapkUMVwy";
	public final static String CHAR_ID_WEIGHT = "j1ikY1U3st";
	public final static String CHAR_ID_FEEDING = "2Ibm6va7I7";
	public final static String CHAR_ID_HEARTRATE = "lutsUGHsug";
	public final static String CHAR_ID_PULSEOXYGEN = "yqIvxyTqDk";
	public final static String CHAR_ID_BLOODPRESSURE = "pQ4t2eh67w";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);


		GridView gridView = (GridView) findViewById(R.id.addGridView);
		gridView.setAdapter(new ImageAdapter(this));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(CustomApplication.getInstance(), ViewActivitySelectDates.class);
				switch(arg2) {
				case 0:
					i.putExtra("charid", CHAR_ID_HEARTRATE);
					break;
				case 1:
					i.putExtra("charid", CHAR_ID_BLOODPRESSURE);
					break;
				case 2:
					i.putExtra("charid", CHAR_ID_PULSEOXYGEN);
					break;
				case 3:
					i.putExtra("charid", CHAR_ID_FEEDING);
					break;
				case 4:
					i.putExtra("charid", CHAR_ID_WEIGHT);
					break;
				case 5:
					i.putExtra("charid", CHAR_ID_MEDICATION);
					break;
				}
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
				R.drawable.heartrate, R.drawable.bloodpressure,
				R.drawable.pulseoxygen, R.drawable.feeding,
				R.drawable.weight, R.drawable.medication
		};
	}    
}
