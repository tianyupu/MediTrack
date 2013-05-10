package org.uwhealthkids.MediTrack;

import java.util.List;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	@SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "Zx2IAp6TTPyM5UYRCr1Q4Q0GD0RyS0IDLzTm0aH0", "Dwj8peVWshOTpzos0Qae9yOBnhmZIMIxv4kJ6oTm");
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(CustomApplication.getInstance());
        EditTextPreference feedingGoalPref = (EditTextPreference) findPreference("pref_feed_goal");
        feedingGoalPref.setSummary("Your current feeding goal is "+sharedPrefs.getString("pref_feed_goal", "not set"));
        
        ListPreference currBabyPref = (ListPreference) findPreference("pref_curr_baby");
        ParseQuery query = new ParseQuery("Baby");
        ParseQuery getBabiesGivenUser = new ParseQuery("BabyUserRel");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        getBabiesGivenUser.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        getBabiesGivenUser.whereEqualTo("user", CustomApplication.getInstance().getCurrUser());
        ParseObject babyObj;
		try {
			babyObj = query.get(currBabyPref.getValue());
			currBabyPref.setSummary("You're currently tracking "+babyObj.getString("fname")+" "+babyObj.getString("surname"));
			List<ParseObject> babies = getBabiesGivenUser.find();
			String[] entries = new String[babies.size()];
			String[] entryValues = new String[babies.size()];
			for (int i = 0; i < babies.size(); i++) {
				ParseObject obj = babies.get(i);
				ParseObject baby = obj.getParseObject("baby").fetchIfNeeded();
				String fname = baby.getString("fname");
				String surname = baby.getString("surname");
				entries[i] = fname+" "+surname;
				entryValues[i] = baby.getObjectId();
			}
			currBabyPref.setEntries(entries);
			currBabyPref.setEntryValues(entryValues);
		} catch (ParseException e) {
			currBabyPref.setSummary("Please connect to the internet to view the current baby or change a baby to track.");
			Toast.makeText(CustomApplication.getInstance(), 
					"No internet connection found. Please check your network settings.", Toast.LENGTH_LONG).show();
		}
		
		DialogPreference babyKey = (DialogPreference) findPreference("pref_baby_key");
		babyKey.setDialogMessage(CustomApplication.getInstance().getCurrBaby().getObjectId());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
	    super.onResume();
	    getPreferenceScreen().getSharedPreferences()
	            .registerOnSharedPreferenceChangeListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPause() {
	    super.onPause();
	    getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
		Preference pref = findPreference(key);
		if (key.equals("pref_feed_goal")) {
			pref.setSummary("Your current feeding goal is "+preferences.getString(key, "not set"));
		}
		else if (key.equals("pref_curr_baby")) {
		    ParseQuery query = new ParseQuery("Baby");
		    query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		    ParseObject babyObj;
			try {
				babyObj = query.get(((ListPreference) pref).getValue());
				pref.setSummary("You're currently tracking "+babyObj.getString("fname")+" "+babyObj.getString("surname"));
				CustomApplication.getInstance().setCurrBaby(babyObj);
				DialogPreference babyKey = (DialogPreference) findPreference("pref_baby_key");
				babyKey.setDialogMessage(CustomApplication.getInstance().getCurrBaby().getObjectId());
			} catch (ParseException e) {
				pref.setSummary("Please connect to the internet to view the current baby or change a baby to track.");
				Toast.makeText(CustomApplication.getInstance(), 
						"No internet connection found. Please check your network settings.", Toast.LENGTH_LONG).show();
			}
			
		}
	}
}
