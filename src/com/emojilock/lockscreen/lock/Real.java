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
	/*************************** Class Attributes ***************************/
	private String realKey;
	
	/*************************** Class Methods ***************************/
	/**
	 * The constructor
	 * @param share	SharedPreference to be used
	 */
	public Real(SharedPreferences share, Controller controller)
	{
		super(share, controller);
		this.realKey = null;
	} /* end constructor */
	
	/**
	 * Check to see if the key matches the stored key
	 */
	@Override
	protected boolean unlock(String key) 
	{		
		String returner = null;
		if(this.realKey == null) realKey = share.getString(PASSWORD_FILE_KEY, null);
		if (key != null) returner = realKey; 
		return (realKey != null && realKey.compareTo(key) == 0);
	} /* end unlock method */

} /* end RealLock class */
