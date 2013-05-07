package org.uwhealthkids.MediTrack;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	@SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(CustomApplication.getInstance());
        EditTextPreference feedingGoalPref = (EditTextPreference) findPreference("pref_feed_goal");
        feedingGoalPref.setSummary("Your current feeding goal is "+sharedPrefs.getString("pref_feed_goal", "not set"));
        
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
