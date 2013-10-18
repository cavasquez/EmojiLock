package com.emojilock.lockscreen.metaGrid;

import java.util.ArrayList;

/*****************************************************************************************************
 * MetaGrid will retain information about the Grid and the placement and ID of emotes and bodies
 *****************************************************************************************************/

public abstract class MetaGrid 
{
	/*************************** Class Attributes ***************************/
	protected ArrayList<Integer>[] meta;		// Meta data
	protected ArrayList<Integer>[] drawableID;	// List of the drawables
	private int metaSize;						// Size of the meta array
	
	/*************************** Class Methods ***************************/
	public MetaGrid(int metaSize)
	{
		this.metaSize = metaSize;
		
		// initialize meta
		meta = new ArrayList[metaSize];
		
		for(int i = 0; i < metaSize; i++)
		{
			meta[i] = new ArrayList<Integer>();
		} /* end for loop */
		
	} /* end MetaGrid constructor */
	
	public abstract Integer getID(int position, Integer type);
	public abstract Integer getSize();
	public abstract Integer getDrawableID(int position, Integer type);
	
} /* end MetaGrid method */
