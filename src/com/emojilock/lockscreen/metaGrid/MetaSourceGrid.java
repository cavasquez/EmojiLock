package com.emojilock.lockscreen.metaGrid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.emojilock.lockscreen.LockScreen;

/*****************************************************************************************************
 * MetaSourceGrid will control information that deals explicitly with the SourceView Grid
 *****************************************************************************************************/

public class MetaSourceGrid extends MetaGrid
{
	/*************************** Class Constants ***************************/
	public static final int SOURCE_ID = 0;		// Used to identify the index in meta that deals with source ID
	public static final int SOURCE_TYPE = 1;	// Used to identify the index in meta that deals with source type
	
	/*************************** Class Attributes ***************************/
	private int emoteCount;						// Number of "emote"'s in grid
	private int bodyCount;						// Number of "body"'s in grid
	
	/*************************** Class Methods ***************************/
	public MetaSourceGrid(int emoteCount, int bodyCount)
	{
		super(2);
		this.emoteCount = emoteCount;
		this.bodyCount = bodyCount;
		
	} /* end constructor */
	
	public MetaSourceGrid(int emoteCount, int bodyCount, int[] emoteDrawables, int[] bodyDrawables)
	{
		this(emoteCount, bodyCount);
		drawableID = new ArrayList[2];
		drawableID[0] = new ArrayList<Integer>();
		drawableID[1] = new ArrayList<Integer>();
		
		for(int i = 0; i < emoteCount; i++)
		{
			drawableID[LockScreen.EMOTE].add(emoteDrawables[i]);
		} /* end loop */
		
		for(int i = 0; i < bodyCount; i++)
		{
			drawableID[LockScreen.BODY].add(bodyDrawables[i]);
		} /* end loop */
		
		initialize();
		
	} /* end overloaded constructor */
	
	/************** Public Methods **************/
	public void initialize()
	{
		/* Randomize the placement of "emote"'s and "body"'s and place them into the GridView sourceGrid */
		LinkedList<Integer> tempSourceID = new LinkedList<Integer>();
		LinkedList<Integer> tempSourceType = new LinkedList<Integer>();
		Random rand = new Random();
		int next = 0;
		
		// First, initialize temporary sources
		for(int i = 0; i < emoteCount; i++)
		{
			tempSourceID.add(i);
			tempSourceType.add(LockScreen.EMOTE);
		} /* end loop */
		
		for(int i = 0; i < bodyCount; i++)
		{
			tempSourceID.add(i);
			tempSourceType.add(LockScreen.BODY);
		} /* end loop */
		
		// Next, create the randomized grid
		meta[SOURCE_ID].clear();
		meta[SOURCE_TYPE].clear();
		
		for (int i = 0; i < (emoteCount + bodyCount); i++)
		{
			next = rand.nextInt(tempSourceID.size()); 
			meta[SOURCE_ID].add(tempSourceID.remove(next));
			meta[SOURCE_TYPE].add(tempSourceType.remove(next));
		} /* end loop */
		
	} /* end initialize */
	
	/*************************** Getters and Setters ***************************/
	@Override
	public Integer getID(int position, Integer type)
	{
		// Get the "ID" of the "emote" or "body" in the provided position
		return meta[SOURCE_ID].get(position);
	} /* end getID method */
	
	public int getType(int index)
	{
		return meta[SOURCE_TYPE].get(index);
	} /* end getType method */
	
	public Integer getDrawableID(int position, Integer type)
	{
		// Find the ID of the drawable in the provided position. Recall, we must lookup the type and id
		return drawableID[meta[SOURCE_TYPE].get(position)].get(meta[SOURCE_ID].get(position));
	} /* end getDrawableID method */
	
	@Override
	public Integer getSize()
	{
		return(emoteCount + bodyCount);
	} /* end getSize method */

} /* end MetaGrid */
