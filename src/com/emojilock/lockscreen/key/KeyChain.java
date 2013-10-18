package com.emojilock.lockscreen.key;

import java.util.ArrayList;

/*****************************************************************************************************
 * KeyChain will be the "controller" for the Key class. It will create the necessary key and provide
 * the create method necessary for the key to be made.
 *****************************************************************************************************/

public class KeyChain 
{
	/*************************** Class Constants ***************************/
	/**
	 * The type of input being received. It can either be ordered (order 
	 * matters) or unordered (order does not matter)
	 */
	public static enum Type
	{
		ORDERED (),
		UNORDERED ();
		
	} /* end Dice enum */
	
	/*************************** Class Attributes ***************************/
	private Type type;				// The key type
	private Key key;					// The key that will be used
	
	/*************************** Class Methods ***************************/
	/**
	 * The constructor of the class. It will take in a Key Type to create
	 * the appropriate key.
	 * @param type	the type of key that needs to be created
	 */
	public KeyChain(Type type)
	{
		this.type = type;
		switch(this.type)
		{
			case ORDERED: this.key = new OrderedKey();
				break;
			case UNORDERED: this.key = new UnorderedKey();
				break;
			default:
				break;
		} /* end switch */
	} /* end constructor */
	
	/**
	 * Return the hash of the password.
	 * @param password	the password as represented by the application.
	 * @return			the hash of the password
	 */
	public String create(ArrayList<Integer>[] password, String salt)
	{
		return key.create(password, salt);
	} /* end create method */
	
	/**
	 * Return the key
	 * @return 	the key
	 */
	public Key getKey()
	{
		return key;
	} /* end getKey method */

} /* end KeyChain class */
