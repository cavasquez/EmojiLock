package com.emojilock.lockscreen.controller;

import com.emojilock.lockscreen.imageAdapter.ImageAdapter;
import com.emojilock.lockscreen.metaGrid.MetaGrid;
import com.emojilock.lockscreen.metaGrid.MetaTrashGrid;

/*****************************************************************************************************
 * TrashController will provide access to various methods from MetaTrashGrid and TrashAdapter
 *****************************************************************************************************/

public class TrashController extends LockController
{
	/*************************** Class Methods ***************************/
	protected TrashController(MetaGrid meta, ImageAdapter adapter) 
	{
		super(meta, adapter);
	} /* end constructor */
	
	public void setTrash()
	{// Set the data to trash
		((MetaTrashGrid)meta).setTrash();
	} /* end setTrash method */
	
	public void removeImage()
	{// Remove any images
		((MetaTrashGrid)meta).removeImage();
	} /* end removeImage method */
	
	public boolean isTrashSet()
	{
		return ((MetaTrashGrid)meta).isTrashSet();
	} /* end isTrashSet method */

} /* end MiscController method */
