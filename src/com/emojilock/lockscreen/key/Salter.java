package com.emojilock.lockscreen.key;

import java.security.SecureRandom;

/*****************************************************************************************************
 * Salter will generate a salt that will be used in password hashes
 *****************************************************************************************************/

public class Salter 
{
	/*************************** Class Constants ***************************/
	private static final int SALT_BYTE_SIZE = Hasher.SALT_BYTE_SIZE;
	
	/*************************** Class Methods ***************************/
	public Salter() {/* Do nothing */} /* end Salter class */
	
	/**
	 * Generate a random value of the established size
	 * @param size	the size of the salt
	 * @return		the salt
	 */
	public static final byte[] makeSalt(int size)
	{
		byte[] salt = new byte[size];
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		return salt;
	} /* end makeSalt method */
	
	/**
	 * Create a salt of the default size (64 bytes = 512 bits)
	 * @return	the salt
	 */
	public static final byte[] makeSalt()
	{
		return Salter.makeSalt(SALT_BYTE_SIZE);
	} /* end overdriven method */
	
} /* end Salter class */
