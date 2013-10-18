package com.emojilock.lockscreen.controller;

import com.emojilock.lockscreen.imageAdapter.ImageAdapter;
import com.emojilock.lockscreen.metaGrid.MetaGrid;

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

} /* end UnlockController class */
