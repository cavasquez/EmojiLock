package com.emojilock.lockscreen.metaGrid;

import com.emojilock.R;

/*****************************************************************************************************
 * MetaUnlockGrid will control information that deals explicitly with the unlockView Grid View
 *****************************************************************************************************/

public class MetaUnlockGrid extends MetaGrid
{
	/*************************** Class Constants ***************************/
	private static final int UNLOCK = R.drawable.unlock;		// The trash drawable id. trash.png was created with Android Asset Studio
	
	/*************************** Class Attributes ***************************/
	private boolean locked;					// Locked status
	
	/*************************** Class Methods ***************************/
	public MetaUnlockGrid() 
	{
		super(1);
		meta[0].add(UNLOCK);
		this.locked = false;
	} /* end constructor */

	@Override
	public Integer getID(int position, Integer type) 
	{
		return meta[0].get(position);
	} /* end getID method */

	@Override
	public Integer getSize() 
	{
		return meta[0].size();
	} /* end getSize method */

	@Override
	public Integer getDrawableID(int position, Integer type) 
	{
		return getID(position, type);
	} /* end getDrawableID method */
	
	/**
	 * Set lock
	 */
	public void lock()
	{
		this.locked = true;
	} /* end lock method */
	
	/**
	 * Remove lock
	 */
	public void unlock()
	{
		this.locked = false;
	} /* end unlock method */
	
	/**
	 * Return locked status
	 * @return	locked status
	 */
	public boolean isLocked()
	{
		return this.locked;
	} /* end isLocked method */
	
} /* end MetaUnlockGrid */
