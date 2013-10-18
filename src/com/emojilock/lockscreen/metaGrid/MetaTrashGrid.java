package com.emojilock.lockscreen.metaGrid;

import com.emojilock.R;

/*****************************************************************************************************
 * MetaMiscGrid will control information that deals explicitly with the miscView Grid View
 *****************************************************************************************************/

public class MetaTrashGrid extends MetaGrid
{
	/*************************** Class Constants ***************************/
	private static final int TRASH = R.drawable.trash;		// The trash drawable id. trash.png was created with Android Asset Studio
	
	/*************************** Class Attributes ***************************/
	private boolean trashIsSet = false;						// Flag that trash is set
	
	/*************************** Class Methods ***************************/
	public MetaTrashGrid() 
	{
		super(1);
		meta[0].add(null);
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
	{// Do nothing
		return getID(position, type);
	} /* end getDrawableID method */
	
	public void setTrash()
	{// Set the data to trash
		meta[0].set(0, TRASH);
		trashIsSet = true;
	} /* end setTrash method */
	
	public void removeImage()
	{// Remove any images
		meta[0].set(0, null);
		trashIsSet = false;
	} /* end removeImage method */
	
	public boolean isTrashSet()
	{
		return trashIsSet;
	} /* end isTrashSet method */

} /* end MetaMiscGrid method */
