package org.uwhealthkids.MediTrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GraphViewActivity extends Activity {

	private List<Float> valuesOneArr;
	private List<Float> valuesTwoArr;
	private List<Calendar> calendarArray;

	private Calendar dateFirst;
	private Calendar dateLast;

	private TextView dateFirstText;
	private TextView dateLastText;

	@SuppressWarnings({ "deprecation" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graphview);

		dateFirstText = (TextView) findViewById(R.id.firstDate);
		dateLastText = (TextView) findViewById(R.id.lastDate);

		valuesOneArr = new ArrayList<Float>();
		valuesTwoArr = new ArrayList<Float>();
		calendarArray = new ArrayList<Calendar>();
		dateLast = Calendar.getInstance();
		dateFirst = Calendar.getInstance();
		Intent intent = getIntent();
		dateLast.set(intent.getIntExtra("lastYear", 0), intent.getIntExtra("lastMonth", 0), 
				intent.getIntExtra("lastDay", 0));
		dateFirst.set(intent.getIntExtra("firstYear", 0), intent.getIntExtra("firstMonth", 0), 
				intent.getIntExtra("firstDay", 0));
		dateFirstText.setText((intent.getIntExtra("firstMonth", 0)+1) + "/" + intent.getIntExtra("firstDay", 0));
		dateLastText.setText((intent.getIntExtra("lastMonth", 0)+1) + "/" + intent.getIntExtra("lastDay", 0));

		Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
		ParseQuery query = new ParseQuery("Record");
		ParseQuery babyquery = new ParseQuery("Baby");
		ParseObject babyObject = null;
		try {
			babyObject = babyquery.get(CustomApplication.getInstance().getCurrBaby().getObjectId().toString());
		} catch (ParseException e1) {
			Log.d("tag", "could not find baby");
		}
		ParseQuery charquery = new ParseQuery("Charact");
		ParseObject charObject = null;
		try {
			charObject = charquery.get(this.getIntent().getExtras().getString("charid"));
		} catch (ParseException e1) {
			Log.d("tag", "could not find char");
		}

		
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.whereEqualTo( "baby" , babyObject);
		query.whereEqualTo( "charact" , charObject);
		query.orderByAscending("createdAt");

		List<ParseObject>parseList = new ArrayList<ParseObject>();
		try {
			parseList = query.find();
		} catch (ParseException e) {
			Log.d("error", "unable to get parselist");
		}

		Iterator<ParseObject> iter = parseList.iterator();
		while(iter.hasNext()) {
			ParseObject po = (ParseObject) iter.next();
			if (po.getNumber("value1") != null && po.getNumber("value2") != null ) {
				valuesOneArr.add(Float.parseFloat(po.getNumber("value1").toString()));
				valuesTwoArr.add(Float.parseFloat(po.getNumber("value2").toString()));
				Calendar c = Calendar.getInstance();
				c.setTime(po.getCreatedAt());
				calendarArray.add(c);
			}
			else {
				valuesOneArr.add(Float.parseFloat(po.getNumber("value1").toString()));
				Calendar c = Calendar.getInstance();
				c.setTime(po.getCreatedAt());
				calendarArray.add(c);
			}
		}

		int indexOne=0;int indexTwo=calendarArray.size()-1;
		boolean foundFirst = false; boolean foundLast = false;

		if (indexOne - indexTwo >= 1) {
			for (int i=0; i < calendarArray.size(); i++) {
				if (!foundFirst && calendarArray.get(i).compareTo(dateFirst)>=0) {
					foundFirst = true;
					indexOne = i;
				}
				if (!foundLast && calendarArray.get(i).compareTo(dateLast)>=0) {
					foundLast = true;
					indexTwo = i;
				}
			}


				calendarArray.subList(indexOne, indexTwo);
				valuesOneArr.subList(indexOne, indexTwo);
				if (valuesTwoArr.size() > indexTwo) {
					valuesTwoArr.subList(indexOne, indexTwo);
				}
			
		}

		float large = 0;
		float small = Float.MAX_VALUE;
		for (float f : valuesOneArr) {
			if (f > large) {
				large = f;
			}
			if (f < small) {
				small = f;
			}
		}
		if (valuesTwoArr.size() > 0) {
			for (float f : valuesTwoArr) {
				if (f < small) {
					small = f;
				}
			}
		}

		String[] verlabels = {String.valueOf(large), String.valueOf(small)};
		String[] horlabels = new String[calendarArray.size()];
		for (int i = 0; i < horlabels.length; i++) {
			horlabels[i] = (calendarArray.get(i).getTime().getMonth()+1) + "/" + calendarArray.get(i).getTime().getDate();
		}

		GraphView g = null;
		if (valuesTwoArr.size() != 0) {
			g = new GraphView(this, valuesOneArr, valuesTwoArr, charObject.getString("name"), horlabels, verlabels, false);
		}
		else {
			g = new GraphView(this, valuesOneArr, charObject.getString("name"), horlabels, verlabels, false);
		}
		LinearLayout lv = (LinearLayout) findViewById(R.id.graphLinearLayout);
		lv.addView(g);
	}
}
