package com.emojilock.lockscreen.controller;

import com.emojilock.lockscreen.imageAdapter.ImageAdapter;
import com.emojilock.lockscreen.metaGrid.MetaGrid;

/*****************************************************************************************************
 * LockController will define the methods InputController and SourceController should implement and
 * define some of the more common methods
 *****************************************************************************************************/

public abstract class LockController 
{
	/*************************** Class Attributes ***************************/
	protected MetaGrid meta;			// The meta information about the grid
	protected ImageAdapter adapter;		// The adapter for the grid
	
	/*************************** Class Attributes ***************************/
	protected LockController(MetaGrid meta, ImageAdapter adapter)
	{
		this.meta = meta;
		this.adapter = adapter;
	} /* end constructor */
	
	/* MetaGrid methods */
	public Integer getID(int position, Integer type)
	{
		return meta.getID(position, type);
	} /* end getID method */
	
	public Integer getSize()
	{
		return meta.getSize();
	} /* end getSize method */
	
	public Integer getDrawableID(int position, Integer type)
	{
		return meta.getDrawableID(position, type);
	} /* end getDrawableID method */
	
	/* Adapter methods */
	
	public void setLayoutParams(int width, int height)
	{
		adapter.setLayoutParams(width, height);
	} /* end setLayoutParams method */
	
	public void setPadding(int left, int top, int right, int bottom)
	{
		adapter.setPadding(left, top, right, bottom);
	} /* end setPadding method */
	
	public void notifyDataSetChanged()
	{
		adapter.notifyDataSetChanged();
	} /* end notifyDataSetChanged */
	
} /* end LockController class */
