package org.uwhealthkids.MediTrack;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
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
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject babyObj;
		try {
			babyObj = query.get(currBabyPref.getValue());
			currBabyPref.setSummary("You're currently tracking "+babyObj.getString("fname")+" "+babyObj.getString("surname"));
		} catch (ParseException e) {
			Toast.makeText(CustomApplication.getInstance(), 
					"No internet connection found. Please check your network settings.", Toast.LENGTH_LONG).show();
		}
        
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
		if (key.equals("pref_feed_goal")) {
			Preference feedingPref = findPreference(key);
			feedingPref.setSummary("Your current feeding goal is "+preferences.getString(key, "not set"));
		}
	}
}
