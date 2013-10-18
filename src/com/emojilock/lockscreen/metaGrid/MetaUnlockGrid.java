package com.emojilock.lockscreen.metaGrid;

import com.emojilock.R;

/*****************************************************************************************************
 * MetaUnlockGrid will control information that deals explicitly with the unlockView Grid View
 *****************************************************************************************************/

public class MetaUnlockGrid extends MetaGrid
{
	/*************************** Class Constants ***************************/
	private static final int UNLOCK = R.drawable.unlock;		// The trash drawable id. trash.png was created with Android Asset Studio
	
	/*************************** Class Methods ***************************/
	public MetaUnlockGrid() 
	{
		super(1);
		meta[0].add(UNLOCK);
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

} /* end MetaUnlockGrid */
