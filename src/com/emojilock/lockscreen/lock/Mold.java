package com.emojilock.lockscreen.lock;

import com.emojilock.EmojiLockService;
import com.emojilock.lockscreen.controller.Controller;

import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Mold will be the Lock that saves the password.
 * @author Carlos
 *
 */
public class Mold extends Lock
{
	/*************************** Class Methods ***************************/
	/**
	 * The constructor
	 * @param share	SharedPreference to be used
	 */
	public Mold(SharedPreferences share, Controller controller)
	{
		super(share, controller);
	} /* end constructor */
	
	/**
	 * Molds unlock will always return true. This method will store the key
	 * and start the the EmojiLockService
	 * @param key	the key that will be stored
	 * @return		true
	 */
	@Override
	protected boolean unlock(String key) 
	{
		boolean correct = false;
		if (key != null) 
		{
			// Store the password
			SharedPreferences.Editor editor = share.edit();
			editor.putString(PASSWORD_FILE_KEY, key);
			editor.commit();
			correct = true;
			
			// Start the EmojiLockService
			controller.getLockScreen().startService(new Intent(controller.getLockScreen(), EmojiLockService.class));
			
		} /* end if */
		
		return correct;
	} /* end unlock method */

} /* end Mold class */
