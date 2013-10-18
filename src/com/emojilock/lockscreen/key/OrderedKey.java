package com.emojilock.lockscreen.key;

import java.util.ArrayList;

import com.emojilock.lockscreen.LockScreen;

/*****************************************************************************************************
 * OrderdKey will define the transformation that needs to take place to obtain the plain text password
 * from the input. The provided password will be built into a string with the following format:
 * E0B0E1B1E2B2...ENBN 
 * where E stands for emote ID and B stands for body ID. The numbers represent their placement in order,
 * where 0 is the leftmost element and N is the rightmost element.
 *****************************************************************************************************/

public class OrderedKey extends Key
{
	/*************************** Class Methods ***************************/
	public OrderedKey()
	{
		super();
	} /* end constructor */
	
	/**
	 * Transform the password into an array that represents the ides of the
	 * emote/body pairs in order from first to last
	 */
	protected String transform(ArrayList<Integer>[] password)
	{// Turn the password into a string
		String plaintext = "";
		boolean error = false; // flag for errors
		Integer emote = null;
		Integer body = null;
		for(int i = 0; i < password[0].size() && !error; i++)
		{
			emote = password[LockScreen.EMOTE].get(i);
			body = password[LockScreen.BODY].get(i);
			
			if(emote == null || body == null) error = true;
			else plaintext += Integer.toString(emote) + Integer.toString(body);
		} /* end for loop */
		
		// If there was an error, return null
		if(error) plaintext = null;
		return plaintext;
	} /* end transform method */

} /* end OrderedKey class */
