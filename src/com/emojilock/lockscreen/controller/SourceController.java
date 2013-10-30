package com.emojilock.lockscreen.controller;

import com.emojilock.lockscreen.imageAdapter.ImageAdapter;
import com.emojilock.lockscreen.metaGrid.MetaGrid;
import com.emojilock.lockscreen.metaGrid.MetaSourceGrid;

/*****************************************************************************************************
 * SourceController will provide access to various methods from MetaSourceGrid and SourceAdapter
 *****************************************************************************************************/

public class SourceController extends LockController
{
	/*************************** Class Methods ***************************/
	protected SourceController(MetaGrid meta, ImageAdapter adapter) 
	{
		super(meta, adapter);
		// TODO Auto-generated constructor stub
	} /* end constructor */
	
	public int getType(int index)
	{
		return ((MetaSourceGrid) meta).getType(index);
	} /* end getType method */
	
	public Integer getDrawableID(int position, Integer type)
	{
		return ((MetaSourceGrid) meta).getDrawableID(position, type);
	} /* end getDrawableID method */
	
	public void print()
	{
		((MetaSourceGrid) meta).print();
	} /* end print method */
	
} /* end SourceController class */
