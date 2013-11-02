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
	
} /* end ProductionLockScreen class */
