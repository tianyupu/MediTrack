package org.uwhealthkids.MediTrack;

import java.util.ArrayList;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class DoctorMainActivity extends ListActivity {
	private ArrayList<String> BabyNames = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private ArrayList<ParseObject> babyObj = new ArrayList<ParseObject>();
	private ParseObject baby; 
	ParseQuery babyquery = new ParseQuery("Baby");


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_doctor_main);


		// get current user
		ParseUser currUser = ParseUser.getCurrentUser();
		//finds the view
		ListView listview = getListView();
		adapter = new ArrayAdapter<String>(listview.getContext(), android.R.layout.simple_list_item_1, BabyNames);
		listview.setAdapter(adapter);
		
		
		//populate the list
		ParseQuery query = new ParseQuery("BabyUserRel");
		query.whereEqualTo("user", currUser);
		query.findInBackground(new FindCallback(){
			public void done(List<ParseObject> relObj, ParseException e){
				if(e == null){
					for(ParseObject obj : relObj){
						try {
							baby= babyquery.get(obj.getParseObject("baby").getObjectId());
							babyObj.add(baby);
							BabyNames.add(baby.getString("fname") + " " + baby.getString("surname"));	

						} catch (ParseException e1) {
							e1.printStackTrace();
						}		
					}
					adapter.notifyDataSetChanged();
				}else
					Toast.makeText(CustomApplication.getInstance(), "Couldn't establish an Internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();	
			}
		});		

		

		//Register for context menu
		registerForContextMenu(listview);

	}

	//This will be invoked when an item in the listview is long
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.doctor_menu, menu);
	}


	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		Intent intent;
		switch(item.getItemId()){
			case R.id.doc_menu_view:
				baby = babyObj.get(info.position);
				CustomApplication.getInstance().setCurrBaby(baby);
				intent = new Intent(this, ViewActivity.class);
				startActivity(intent);
				break;
			case R.id.doc_menu_sum:
				baby = babyObj.get(info.position);
				CustomApplication.getInstance().setCurrBaby(baby);
				intent = new Intent(this, SummActivity.class);
				startActivity(intent);
				Toast.makeText(this, "sum : " + BabyNames.get(info.position)  , Toast.LENGTH_SHORT).show();
				break;
		}

		return true;
	}



}



