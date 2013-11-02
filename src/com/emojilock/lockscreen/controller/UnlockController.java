package com.emojilock.lockscreen.controller;
import com.emojilock.lockscreen.imageAdapter.ImageAdapter;
import com.emojilock.lockscreen.metaGrid.MetaGrid;
import com.emojilock.lockscreen.metaGrid.MetaUnlockGrid;

/*****************************************************************************************************
 * UnlockController will provide access to various methods from MetaUnlockGrid and UnlockAdapter
 *****************************************************************************************************/

public class UnlockController extends LockController
{	
	/*************************** Class Methods ***************************/
	protected UnlockController(MetaGrid meta, ImageAdapter adapter) 
	{
		super(meta, adapter);
	} /* end constructor */
	
	/**
	 * Set lock
	 */
	public void lock()
	{
		((MetaUnlockGrid)meta).lock();
	} /* end lock method */
	
	/**
	 * Remove lock
	 */
	public void unlock()
	{
		((MetaUnlockGrid)meta).unlock();
	} /* end unlock method */
	
	/**
	 * Return locked status
	 * @return	locked status
	 */
	public boolean isLocked()
	{
		return ((MetaUnlockGrid)meta).isLocked();
	} /* end isLocked method */

} /* end UnlockController class */
