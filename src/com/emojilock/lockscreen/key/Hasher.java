package com.emojilock.lockscreen.key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*****************************************************************************************************
 * Hasher will use the SHA2 hash algorithm and a salt to create a hash of the provided string with
 * the create method.
 * 
 * Please note that the code made here was heavily influenced by crackstation.net and mkyong. The code 
 * can be found here: 
 * https://crackstation.net/hashing-security.htm#javasourcecode
 * http://www.mkyong.com/java/java-sha-hashing-example/
 * 
 * The above sites were used as reference to ensure that proper implementation of salting and hashing
 * was used in this class.
 *****************************************************************************************************/

public class Hasher 
{
	/*************************** Class Constants ***************************/
	private static final String HASH = "SHA-512";
	public static final int SALT_BYTE_SIZE = 64;		// 512 bits
    public static final int HASH_BYTE_SIZE = 64;		// 512 bits
    public static final int HASH_ITERATIONS = 5;
    
    /*************************** Class Methods ***************************/
    /**
     * The constructor for the class does nothing.
     */
    public Hasher(){/* Do nothing */} /* end constructor */
    
    /**
     * hash will return a hash of the password prepended with the salt
     * @param password	the password that will be hashed
     * @param salt		the salt used to salt the password
     * @return			the hash value of the password + salt
     * @throws NoSuchAlgorithmException 
     */
    public static final String hash(String password, String salt) throws NoSuchAlgorithmException
    {
    	MessageDigest digest = MessageDigest.getInstance(HASH);
    	digest.reset();
    	byte[] hash = digest.digest((password + salt).getBytes());

    	for(int i = 0; i < HASH_ITERATIONS - 1; i++)
    	{
    		hash = digest.digest(hash);
    	} /* end for loop */
    	
    	return getHex(hash);
    } /* end hash method */
    
    /**
     * Return a String that contains the hex representation of the provided
     * bytes.
     * @param input	byte(s) that will be converted to hex
     * @return		a hex string of the input
     */
    public static final String getHex(byte[] input)
    {
    	StringBuffer output = new StringBuffer();
    	for(int i = 0; i < input.length; i++)
    	{
    		output.append(Integer.toString((input[i] & 0xff) + 0x100, 16).substring(1));
    	} /* end for loop */
    	
    	return output.toString();
    } /* end getHex method */
    
    /**
     * Return the byte array of the hex string
     * @param hex	the hex string
     * @return		the byte array of the hex string
     */
    public static final byte[] hexToBytes(String hex)
    {
    	/* Note that the code here was largely developed by
    	 * user Dave L. on stackoverflow.com.
    	 * Source: http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
    	 */
    	
    	byte[] output = new byte[hex.length()/2];
    	for(int i = 0; i < output.length; i+=2)
    	{
    		output[i/2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + (Character.digit(hex.charAt(i+1), 16)));
    	} /* end for loop */
    	
    	return output;
    } /* end getBytes method */  
    
    /**
     * Take in a byte array that was a hex string and produce the non hex string of it
     * @param hex	the hex byte array
     * @return		the string equivilent of the hex (non hex)
     */
    public static final String byteToString(byte[] hex)
    {
    	StringBuffer hexString = new StringBuffer();
    	for(int i = 0; i < hex.length; i++) hexString.append((char)(hex[i]));
    	return hexString.toString();
    } /* end hexToString method */
    
} /* end Hasher class */
