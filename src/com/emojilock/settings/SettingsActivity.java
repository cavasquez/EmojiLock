package com.emojilock.settings;

import android.app.Activity;
import android.os.Bundle;


/**
 * SettingsActivity will be the activity for settings
 * @author Carlos
 *
 */
public class SettingsActivity extends Activity
{
	/*************************** Class Methods ***************************/
	/**
	 * Load up the preferences and add necessary listeners
	 */	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    } /* end onCreate method */

} /* end SettingsActivity class */
