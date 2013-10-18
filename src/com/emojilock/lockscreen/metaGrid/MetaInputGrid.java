package com.emojilock.lockscreen.metaGrid;

import java.util.ArrayList;

import com.emojilock.lockscreen.LockScreen;

/*****************************************************************************************************
 * MetaInputGrid will control information that deals explicitly with the InputView GridView
 *****************************************************************************************************/

public class MetaInputGrid extends MetaGrid
{
	/*************************** Class Constants ***************************/
	public static final int EMOTE = LockScreen.EMOTE;		// Used to identify the index in meta that deals with emote
	public static final int BODY = LockScreen.BODY;			// Used to identify the index in meta that deals with body;
	
	/*************************** Class Attributes ***************************/
	private int size;						// The current size of the grid
	private int[][] drawables;				// The list of drawables
	
	/*************************** Class Methods ***************************/
	public MetaInputGrid(int[] emoteDrawables, int[] bodyDrawables) 
	{
		super(2);
		
		// Create drawables
		drawables = new int[2][];
		drawables[EMOTE] = emoteDrawables;
		drawables[BODY] = bodyDrawables;
		
		drawableID = new ArrayList[2];
		drawableID[EMOTE] = new ArrayList<Integer>();
		drawableID[BODY] = new ArrayList<Integer>();
	} /* end constructor */
	
	public void replaceInput(int id, int type, int position)
	{
		boolean fail = false;	// check for failures in the process
		// First, check if position exists
		if(position > size || size < 0) fail = true;	// the position cannot surpass the size the the grid
		else if (position > meta[type].size()) fail = true;	// if the position is greater than the type, it cannot exist
		
		// Replace the element
		if(!fail)
		{
			meta[type].set(position, id);
			drawableID[type].set(position, drawables[type][id]);
		} /* end if */
	
	} /* end addInput method */
	
	/*************************** Adders ***************************/
	public void add(Integer emoteID, Integer bodyID)
	{// Adds to the front of the list
		size++;
		meta[EMOTE].add(emoteID);
		meta[BODY].add(bodyID);
		this.addDrawable(emoteID, bodyID);
	} /* end add method */
	
	public void add(Integer emoteID, Integer bodyID, int position)
	{// add at the given position
		size++;
		meta[EMOTE].add(position, emoteID);
		meta[BODY].add(position, bodyID);
		this.addDrawable(emoteID, bodyID, position);
	} /* end add method */
	
	public void addInput(int id, int type)
	{
		// First, check to see if there are any null items in the list of the given type.
		
		boolean nullExists = false;
		int nullPosition = 0;
		
		for (int i = 0; i < size && !nullExists; i++)
		{
			if(meta[type].get(i) == null)
			{
				nullPosition = i;
				nullExists = true;
			} /* end if */
		} /* end for loop */
		
		
		if(nullExists)
		{// If a null exists, replace the first null in the list
			meta[type].set(nullPosition, id);
			drawableID[type].set(nullPosition, drawables[type][id]);
		} /* end if */
		else
		{// If no nulls exist, extend the list
			Integer add[] = new Integer[2];
			add[type] = id;
			add[invert(type)] = null;
			this.add(add[EMOTE], add[BODY]);
		} /* end else */
		
	} /* end addInput method */
	
	private void addDrawable(Integer emoteID, Integer bodyID)
	{
		if (emoteID != null) drawableID[EMOTE].add(drawables[EMOTE][emoteID]);
		else drawableID[EMOTE].add(null);
		if (bodyID != null) drawableID[BODY].add(drawables[BODY][bodyID]);
		else drawableID[BODY].add(null);
	} /* end addDrawables method */
	
	public void addDrawable(Integer emoteID, Integer bodyID, int position)
	{
		if (emoteID != null) drawableID[EMOTE].add(position, drawables[EMOTE][emoteID]);
		else drawableID[EMOTE].add(position, null);
		if (bodyID != null) drawableID[BODY].add(position, drawables[BODY][bodyID]);
		else drawableID[BODY].add(position, null);
	} /* end addDrawables method */
	
	/*************************** Removals ***************************/
	public void remove(int position)
	{
		size--;
		meta[EMOTE].remove(position);
		meta[BODY].remove(position);
		this.removeDrawable(position);
	} /* end remove method */
	
	public void removeDrawable(int position)
	{
		drawableID[EMOTE].remove(position);
		drawableID[BODY].remove(position);
	} /* end removeDrawable method */
	
	public boolean removeInput(int type, int position)
	{
		boolean fail = false; // check for failures in the process
		// First, check to see if the position is out of bounds
		if(position > size || size < 0) fail = true;	// the position cannot surpass the size the the grid
		
		// Second, check to see if the "item" is the last "item" in the position (ie, only body or only emote
		else if (meta[invert(type)].get(position) == null)
		{// The last "item" is being removed. shift everything to the left and decrement size
			remove(position);
		} /* end else if */
		else
		{// The last "item" is not being removed. Nullify the current "item" and its drawable
			meta[type].set(position, null);
			drawableID[type].set(position, null);
		} /* end else */
	
		return fail;
		
	} /* end removeInput method */
	
	/*************************** Prints ***************************/
	public void printBody()
	{
		System.out.println("MetaInputGrid Body: " + meta[BODY].toString());
	} /* end printBody method */
	
	public void printEmote()
	{
		System.out.println("MetaInputGrid Emote: " + meta[EMOTE].toString());
	} /* end printBody method */
	
	public void printDrawables()
	{
		System.out.println("MetaInputGrid B Drawables: " + drawableID[BODY].toString());
		System.out.println("MetaInputGrid E Drawables: " + drawableID[EMOTE].toString());
	} /* end printDrawables method */
	
	/*************************** Private Methods ***************************/
	protected int invert(int type)
	{
		int returner;
		// Return the "other" type
		if (type == EMOTE) returner = BODY;
		else returner = EMOTE;
		
		return returner;
	} /* end invert method */

	/*************************** Getters and Setters ***************************/
	@Override
	public Integer getID(int position, Integer type) 
	{
		return meta[type].get(position);
	} /* end getID method */

	@Override
	public Integer getSize() 
	{
		return size;
	} /* end getSize method */

	@Override
	public Integer getDrawableID(int position, Integer type) 
	{
		return drawableID[type].get(position);
	} /* end getDrawableID method */
	
	public ArrayList<Integer> getEmoteIDs()
	{
		return meta[EMOTE];
	} /* end getEmoteIDs */
	
	public ArrayList<Integer> getBodyIDs()
	{
		return meta[BODY];
	} /* end getBodyIDs */
	
	public Integer[] getDrawables(int type)
	{
		return (Integer[]) drawableID[type].toArray(new Integer[drawableID[type].size()]);
	} /* end getDrawables method */
	
	public void setDrawables(int type, Integer[] drawables)
	{
		drawableID[type].clear();
		for (int i = 0; i < drawables.length; i++)
		{
			drawableID[type].add(drawables[i]);
		} /* end for loop */
		
	} /* end setDrawables method */
	
	public ArrayList<Integer>[] getInput()
	{
		return meta;
	} /* end getInput method */
	
} /* end MetaInputGrid class */
