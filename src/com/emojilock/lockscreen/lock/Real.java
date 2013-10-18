package com.emojilock.lockscreen.lock;

import com.emojilock.lockscreen.controller.Controller;

import android.content.SharedPreferences;

/**
 * Real will be the Lock that validates the password
 * @author Carlos
 *
 */
public class Real extends Lock
{
	/*************************** Class Methods ***************************/
	/**
	 * The constructor
	 * @param share	SharedPreference to be used
	 */
	public Real(SharedPreferences share, Controller controller)
	{
		super(share, controller);
	} /* end constructor */
	
	/**
	 * Check to see if the key matches the stored key
	 */
	@Override
	protected boolean unlock(String key) 
	{		
		String realKey = null;
		if (key != null) realKey = share.getString(PASSWORD_FILE_KEY, null);
		return (realKey != null && realKey.compareTo(key) == 0);
	} /* end unlock method */

} /* end RealLock class */
