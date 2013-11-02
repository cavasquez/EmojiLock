package com.emojilock.lockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.key.Hasher;
import com.emojilock.lockscreen.key.Salter;
import com.emojilock.lockscreen.lock.Lock;
import com.emojilock.lockscreen.lock.Vault;

/**
 * NewLockScreen will create a new salt, store it, and create a Mold for a lock which will
 * be used to create a new password.
 * 
 * @author Carlos
 */

public class NewLockScreen extends LockScreen
{
	/*************************** Class Methods ***************************/
	@Override
	public void makeLock(Controller controller, Activity parent) 
	{
		SharedPreferences pref = parent.getSharedPreferences(LockScreen.PREFERENCE_NAME, Context.MODE_PRIVATE);
		LockScreen.production = false;
		
		// Create a new salt and store it
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(Lock.SALT_FILE_KEY, Hasher.getHex(Salter.makeSalt()));
		editor.commit();
		
		// Make the mold
		
		controller.makeVault(Vault.Type.MOLD, pref);
	} /* end makeLock method */

} /* end NewLockScreen class */
