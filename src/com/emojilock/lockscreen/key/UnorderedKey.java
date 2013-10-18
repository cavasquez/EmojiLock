package com.emojilock.lockscreen.key;

import java.util.ArrayList;

import com.emojilock.lockscreen.LockScreen;

/*****************************************************************************************************
 * UnorderdKey will define the transformation that needs to take place to obtain the plain text password
 * from the input. The provided password will be built into a string with the following format:
 * E0B0E0B1E0B2...ENB0ENB1ENB2...ENBN 
 * where a group EIBI stands for the number of times the combination of EIBI has ocurred. Note that 
 * there are 8 types of emotes and / types of bodies. Hence, we can represent each as a 3 bit number.
 * Thus, a combination of numbers would provide a 6 bit id (63 numbers).
 * TODO: TEST THIS!
 *****************************************************************************************************/

public class UnorderedKey extends Key
{
	/*************************** Class Methods ***************************/
	public UnorderedKey()
	{
		super();
	} /* end constructor */
	
	/**
	 * Transform the password into an array that represents the number of 
	 * times a emote/body combination has occurred.
	 */
	@Override
	protected String transform(ArrayList<Integer>[] password)
	{// Turn the password into a string
		String plaintext = "";
		boolean error = false;
		Integer emote = null;
		Integer body = null;
		int combo[] = new int[LockScreen.EMOTE_COUNT * LockScreen.BODY_COUNT]; // Get the number of combinations possible
		
		// Work under the assumption that there is an equal number
		// of bodies and emotes
		for(int i = 0; i < password[0].size() && !error; i++)
		{
			emote = password[LockScreen.EMOTE].get(i);
			body = password[LockScreen.BODY].get(i);
			
			if(emote == null || body == null) error = true;
			else combo[getID(emote, body)]++;
		} /* end for loop */
		
		// If there was an error, return null
		if(error) plaintext = null;
		// create plaintext password
		else for(int i = 0; i < combo.length; i++) plaintext += Integer.toString(combo[i]);
		
		return plaintext;
	} /* end transform method */
	
	private int getID(int emote, int body)
	{// get the value of the emote body (EB) as if it were binary -> decimal
		// Recall that 8 = 2*2*2 which is the equivilent of shifting the
		// emote three digits to the left in binary (which is our goal)s
		return (emote*8 + body);
		
	} /* end getID method */

} /* end UnorderedKey class */
