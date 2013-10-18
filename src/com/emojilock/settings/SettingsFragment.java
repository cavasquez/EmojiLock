package com.emojilock.settings;

import com.emojilock.R;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * SettingsFragment will be the PrefernceFragment for SettingsActivity
 * @author Carlos
 *
 */

public class SettingsFragment extends PreferenceFragment
{
	/*************************** Class Methods ***************************/
	/**
	 * Load up the preferences from preferences.xml
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        this.setListeners();
    } /* end onCreate method */
	
	/**
	 * Sets the listeners that will be used by the preferences
	 */
	private void setListeners()
	{
		Preference enable = this.findPreference(this.getString(R.string.enable_key));
		enable.setOnPreferenceClickListener(new OnEnableClickListener(this.getActivity()));
		
	} /* end setListeners method */

} /* end SettingsFragment class */
