package com.emojilock.lockscreen.controller;

import java.util.ArrayList;

import com.emojilock.lockscreen.imageAdapter.ImageAdapter;
import com.emojilock.lockscreen.metaGrid.MetaGrid;
import com.emojilock.lockscreen.metaGrid.MetaInputGrid;

/*****************************************************************************************************
 * InputController will provide access to various methods from MetaInputGrid and InputAdapter
 *****************************************************************************************************/

public class InputController extends LockController
{
	/*************************** Class Methods ***************************/
	public ArrayList<Integer>[] getInput()
	{
		return ((MetaInputGrid) meta).getInput();
	} /* end getInput method */
	
	protected InputController(MetaGrid meta, ImageAdapter adapter) 
	{
		super(meta, adapter);
	} /* end constructor */
	
	public void add(Integer emoteID, Integer bodyID)
	{
		((MetaInputGrid) meta).add(emoteID, bodyID);
	} /* end add method */
	
	public void add(Integer emoteID, Integer bodyID, int position)
	{
		((MetaInputGrid) meta).add(emoteID, bodyID, position);
	} /* end add method */
	
	public void addDrawable(Integer emoteID, Integer bodyID, int position)
	{
		((MetaInputGrid) meta).addDrawable(emoteID, bodyID, position);
	} /* end addDrawable method */
	
	public void setDrawables(int type, Integer[] drawables)
	{
		((MetaInputGrid) meta).setDrawables(type, drawables);
	} /* end add method */
	
	public void removeDrawable(int position)
	{
		((MetaInputGrid) meta).removeDrawable(position);
	} /* end removeDrawables method */
	
	public Integer[] getDrawables(int type)
	{
		return ((MetaInputGrid) meta).getDrawables(type);
	} /* end add method */
	
	public void addInput(int id, int type)
	{
		((MetaInputGrid) meta).addInput(id, type);
	} /* end addInput method */
	
	public void replaceInput(int id, int type, int position)
	{
		((MetaInputGrid) meta).replaceInput(id, type, position);
	} /* end replaceInput method */
	
	/**
	 * Removes input of the given type at the given position from the data structure
	 * @param type		input type (BODY or EMOTE)
	 * @param position	position of input
	 * @return			success or failure of removal
	 */
	public boolean removeInput(int type, int position)
	{
		return ((MetaInputGrid) meta).removeInput(type, position);
	} /* end removeInput method */
	
	public void remove(int position)
	{
		((MetaInputGrid) meta).remove(position);
	} /* end remove method */
	
	public ArrayList<Integer> getEmoteIDs()
	{
		return ((MetaInputGrid) meta).getEmoteIDs();
	} /* end getEmoteIDs method */
	
	public ArrayList<Integer> getBodyIDs()
	{
		return ((MetaInputGrid) meta).getBodyIDs();
	} /* end getBodyIDs method */
	
	public void print()
	{
		((MetaInputGrid) meta).printBody();
		((MetaInputGrid) meta).printEmote();
	} /* end print method */
	
	public void printDrawables()
	{
		((MetaInputGrid) meta).printDrawables();
	} /* end printDrawables method */
	
} /* end InputController class */
