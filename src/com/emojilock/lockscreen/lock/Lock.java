package com.emojilock.lockscreen.lock;

import java.util.ArrayList;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.key.Key;
import android.content.SharedPreferences;

/*****************************************************************************************************
 * Lock is an abstract class that will allow for multiple kinds of Locks to be used by the Vault.
 *****************************************************************************************************/

public abstract class Lock 
{
	/*************************** Class Constants ***************************/
	protected static final String PASSWORD_FILE_KEY = "com.example.emojilock.password";
	public static final String SALT_FILE_KEY = "com.example.emojilock.salt";
	
	/*************************** Class Attributes ***************************/
	protected SharedPreferences share;
	private String salt;
	protected Controller controller;	
	
	/*************************** Class Methods ***************************/
	/**
	 * The constructor will store the SharedPreferences object as an attributes
	 * @param share
	 */
	public Lock(SharedPreferences share, Controller controller)
	{
		this.share = share;
		this.controller = controller;
	} /* end constructor */
	
	/**
	 * Returns the salt
	 * @return	passwords salt
	 */
	protected String getSalt()
	{
		String salt = share.getString(SALT_FILE_KEY, null);
		return salt;
	} /* end getSalt method */

	/**
	 * unlock will try to unlock the vault
	 * @param password	the password used to create the key
	 * @param key		the key used to unlock the vault
	 * @return			returns the status of the matching of the key
	 */
	public boolean unlock(ArrayList<Integer>[] password, Key key)
	{
		if(this.salt == null) this.salt = this.getSalt();
		String keyString = key.create(password, this.salt);
		boolean unlocked = false;
		if (keyString != null) unlocked = this.unlock(keyString);
		return unlocked;
	} /* end unlock method */
	
	/**
	 * The unlock method that will be used by the subclass
	 * @param key	the string that will be used as the hashed key
	 * @return		returns that status of the matching of the key
	 */
	protected abstract boolean unlock(String key);
} /* end Lock class */
