package org.uwhealthkids.MediTrack;

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

public class AddActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		GridView gridView = (GridView) findViewById(R.id.addGridView);
		gridView.setAdapter(new ImageAdapter(this));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
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
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
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
