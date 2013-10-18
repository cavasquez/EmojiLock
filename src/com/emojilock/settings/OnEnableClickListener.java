package com.emojilock.settings;

import com.emojilock.EmojiLockService;
import com.emojilock.R;
import com.emojilock.lockscreen.NewLockScreen;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceManager;

/**
 * OnEnableClickListener will instruct the enable preference to start an instance of LockScreen
 * when enabled and to disable the ordering preference when enabled. The instance of LockScreen
 * should create the salt as well as ask the user to create a password.
 * 
 * @author Carlos
 */
public class OnEnableClickListener implements OnPreferenceClickListener
{
	/*************************** Class Attributes ***************************/
	private Context context;
	
	/*************************** Class Methods ***************************/
	/**
	 * Constructor will add the context 
	 * @param context	the context
	 */
	public OnEnableClickListener(Context context)
	{
		this.context = context;
	} /* end constructor */
	
	/**
	 * Start a NewLockScreen activity if enabled or a ProductionLockScreen 
	 * if disabled
	 * @param preference the preference
	 */
	@Override
	public boolean onPreferenceClick(Preference preference) 
	{
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
		boolean enabled = preferences.getBoolean(this.context.getString(R.string.enable_key), false);
		
		if(enabled) this.context.startActivity(new Intent(this.context, NewLockScreen.class));
		// Disable the EmojiLock service
		else context.stopService(new Intent(context, EmojiLockService.class));


		return false;
	} /* end onPreferenceClick listener */

} /* end OnEnableClickListener class */
