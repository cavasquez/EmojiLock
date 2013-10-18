package com.emojilock.lockscreen.key;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/*****************************************************************************************************
 * Key will be used in conjunction with Lock. Key will generate the appropriate hash with the provided
 * input and retrieve the salt used to salt the password.
 * 
 * Specifically, Key will get the salt created when the app started the process to accept a new password
 * and pass the salt along with the interpretation to Hasher to get the hash.
 *****************************************************************************************************/
public abstract class Key 
{	
	/*************************** Class Attributes ***************************/
	protected String salt;	// The salt
	
	/*************************** Class Methods ***************************/
	/**
	 * The constructor of the Key class.
	 */
	public Key() {/*Do nothing*/} /* end constructor */
	
	/**
	 * Returns a salted SHA-512 hash of the password.
	 * @param password	the password to the hash
	 * @return			a salted SHA-512 hash of the password
	 */
	public final String create(ArrayList<Integer>[] password, String salt)
	{ /* Create the hash */
		String hash = null;
		String plaintext = transform(password);

		// Check if plaintext is valid
		if(plaintext != null)
		{// plaintext is valid. continue
			try 
			{
				hash = Hasher.hash(plaintext, salt);
			} /* end try */ 
			catch (NoSuchAlgorithmException e) 
			{
				e.printStackTrace();
			} /* end catch */
		} /* end if */

		return hash;
	} /* end create method */
	
	/**
	 * An abstract method that should return the classes interpretation
	 * of the password.
	 * @param password	the password from the Lock Screen that will interpreted.
	 * @return			the classes interpretation of the password.
	 */
	protected abstract String transform(ArrayList<Integer>[] password); // Transform the key accordingly
	
} /* end Key class */
