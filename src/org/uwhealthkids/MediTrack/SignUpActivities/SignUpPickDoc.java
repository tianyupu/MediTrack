package org.uwhealthkids.MediTrack.SignUpActivities;

import java.util.ArrayList;
import java.util.List;

import org.uwhealthkids.MediTrack.CustomApplication;
import org.uwhealthkids.MediTrack.PatientActivity;
import org.uwhealthkids.MediTrack.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class SignUpPickDoc extends Activity{
	private ArrayAdapter<String> adapter;
	private ArrayList<String> doctorNames = new ArrayList<String>();
	private ArrayList<ParseObject> doctorsObj = new ArrayList<ParseObject>(); 
	private ParseQuery docquery = new ParseQuery("_User");
	Button button;
	ListView listview;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up_pickdoc);

		boolean doctor = true;

		findViewsById();
		//finds the view
		ListView listview = (ListView)findViewById(R.id.list);
		adapter = new ArrayAdapter<String>(listview.getContext(), android.R.layout.simple_list_item_multiple_choice, doctorNames);
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listview.setAdapter(adapter);
		
		
		

		//populate the list with doctors
		docquery.whereEqualTo("doctor", doctor);
		docquery.findInBackground(new FindCallback(){
			public void done(List<ParseObject> docObj, ParseException e){
				if(e == null){
					for(ParseObject obj : docObj){
						doctorNames.add(obj.getString("fname") + " " + obj.getString("surname"));
						doctorsObj.add(obj);
					}
					adapter.notifyDataSetChanged();
				}else
					Toast.makeText(CustomApplication.getInstance(), e.getMessage(), Toast.LENGTH_LONG).show();	
			}
		});		

	}
	 private void findViewsById() {
	        listview = (ListView) findViewById(R.id.list);
	        button = (Button) findViewById(R.id.button1);
	    }
	
	public void onFinishedClicked(View v){
		int postion= 0;
		ParseObject currbaby = CustomApplication.getInstance().getCurrBaby();

		SparseBooleanArray checked = listview.getCheckedItemPositions();
		for(int i = 0; i<checked.size(); i++){
			postion = checked.keyAt(i);
			if(checked.valueAt(i)){
				ParseObject babyuserrel = new ParseObject("BabyUserRel");
				babyuserrel.put("user", doctorsObj.get(postion));
				babyuserrel.put("baby", currbaby);
				babyuserrel.saveInBackground();
			}
		}
		
		Intent intent = new Intent(this, PatientActivity.class);
		startActivity(intent);
		finish();
	}

}
