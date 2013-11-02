package com.emojilock.lockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.lock.Vault;

/**
 * ProductionLockScreen will create a Real lock that checks for correct input
 * 
 * @author Carlos
 */

public class ProductionLockScreen extends LockScreen
{
	/*************************** Class Methods ***************************/
	@Override
	public void makeLock(Controller controller, Activity parent) 
	{
		// Make the real lock
		SharedPreferences pref = parent.getSharedPreferences(LockScreen.PREFERENCE_NAME, Context.MODE_PRIVATE);
		controller.makeVault(Vault.Type.REAL, pref);
		
		LockScreen.production = true;
		
	} /* end makeLock method */
	
	/**
	 * Checks to see if u locked out. If so, lockout the user
	 */
	private void checkLockoutStatus()
	{
		
	} /* end checkLockoutStatus method */
	
	/**
	 * Lockout the user based on the remaining time
	 */
	private void lockout()
	{
		
	} /* end lockout method */
	
} /* end ProductionLockScreen class */
