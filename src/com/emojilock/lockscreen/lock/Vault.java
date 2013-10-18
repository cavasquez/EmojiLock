package com.emojilock.lockscreen.lock;

import java.util.ArrayList;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.key.Key;
import android.content.SharedPreferences;

/*****************************************************************************************************
 * Vault will restrict/allow interactions to the Lock and hold the specified lock.
 *****************************************************************************************************/

public class Vault 
{
	/*************************** Class Constants ***************************/
	/**
	 * The type of key to be used. A reak key actually checks for authenticity.
	 * The mold will make a "copy" of the key(ie the hash).
	 */
	public static enum Type
	{
		REAL(),
		MOLD();
		
	} /* end Dice enum */
	
	/*************************** Class Attributes ***************************/
	private Type type;				// The type of key
	private Lock lock;				// The lock to the Vault
	
	/*************************** Class Methods ***************************/
	/**
	 * The constructor will create the necessary key as determined by the type
	 * @param type	the type of key that should be made
	 * @param share	the SharedPreferences object that should be used
	 */
	public Vault(Type type, Controller controller,SharedPreferences share)
	{
		this.type = type;
		switch(this.type)
		{
			case REAL: this.lock = new Real(share, controller);
				break;
			case MOLD: this.lock = new Mold(share, controller);
				break;
			default:
				break;
		} /* end switch */
		
	} /* end constructor */
	
	/**
	 * Check to see if the key is valid
	 * @param key	the key that will be checked
	 * @return		that status of the key match
	 */
	public boolean unlock(ArrayList<Integer>[] password, Key key)
	{
		return lock.unlock(password, key);
	} /* end the unlock method */
	
	/**
	 * Returns the passwords salt
	 * @return	the passwords salt
	 */
	public String getSalt()
	{
		return lock.getSalt();
	} /* end getSalt method */
	
} /* end Vault class */
 