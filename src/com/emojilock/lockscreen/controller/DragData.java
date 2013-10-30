package com.emojilock.lockscreen.controller;

import java.util.Arrays;

import android.view.View;

import com.emojilock.lockscreen.LockScreen;

/*****************************************************************************************************
 * DragData will hold pertinent information about dragged objects
 *****************************************************************************************************/

public class DragData
{
	/*************************** Class Attributes ***************************/
	private LockScreen.VIEW viewType;		// The view prividng the "item(s)"
	private int position;					// The position of the "item" on the grid
	private int id;							// The id of the "item"
	private int type;						// The type of the "item"
	private View view;						// The view being dragged
	private Integer pivot;					// The pivot (blank spot) used when dragging an input
	private Integer[] emoteDrawables;		// A copy of the original emote drawables
	private Integer[] bodyDrawables;		// A copy of the original body drawbles
	
	/*************************** Class Methods ***************************/
	private DragData(View view, LockScreen.VIEW viewType, int position, int id, int type)
	{
		this.view = view;
		this.viewType = viewType;
		this.position = position;
		this.id = id;
		this.type = type;
	} /* end constructor */
	
	public DragData(View view, LockScreen.VIEW viewType, int position) 
	{
		this(view, viewType, position, -1, -1);
	} /* end overloaded constructor */
	
	public DragData(View view, LockScreen.VIEW viewType, int id, int type)
	{
		this(view, viewType, -1, id, type);
	} /* end overloaded constructor */
	
	public LockScreen.VIEW getViewType()
	{
		return viewType;
	} /* end getViewType method */
	
	public int getPosition()
	{
		return position;
	} /* end getIDs method */
	
	public int getID()
	{
		return id;
	} /* end getID method */
	
	public int getType()
	{
		return type;
	} /* end getType method */
	
	public View getView()
	{
		return view;
	} /* end view method */
	
	public void setData(DragData data)
	{
		this.view = data.getView();
		this.id = data.getID();
		this.type = data.getType();
		this.position = data.getPosition();
		this.viewType = data.getViewType();
	} /* end setData method */
	
	public Integer getPivot()
	{
		return pivot;
	} /* end getPivot method */

	public void setPivot(Integer pivot)
	{
		this.pivot = pivot;
	} /* end setPivot method */
	
	public void setEmoteDrawables(Integer[] drawables)
	{
		this.emoteDrawables = drawables;
	} /* end setEmoteDrawables method */
	
	public Integer[] getEmoteDrawables()
	{
		return emoteDrawables;
	} /* end getEmoteDrawables method */

	public void setBodyDrawables(Integer[] drawables)
	{
		this.bodyDrawables = drawables;
	} /* end setBodyDrawables method */
	
	public Integer[] getBodyDrawables()
	{
		return bodyDrawables;
	} /* end getBodyDrawables method */
	
} /* end DragData method */
