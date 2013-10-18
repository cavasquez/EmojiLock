package com.emojilock.lockscreen.controller;

import android.view.View;

import com.emojilock.lockscreen.LockScreen;

/*****************************************************************************************************
 * Controller for DragData 
 *****************************************************************************************************/

public class DragController 
{
	/*************************** Class Attributes ***************************/
	private DragData drag;		// The controller data
	
	/*************************** Class Methods ***************************/
	public DragController()
	{
		this.drag = new DragData(null, null, -1, -1);
	} /* end constructor */
	
	public void set(DragData drag)
	{
		this.drag = drag;
	} /* end DragData method */
	
	public void clear()
	{
		this.drag.setData(new DragData(null, null, -1, -1));
	} /* end clearDragData method */
	
	public LockScreen.VIEW getViewType()
	{
		return drag.getViewType();
	} /* end getViewType method */
	
	public int getPosition()
	{
		return drag.getPosition();
	} /* end getIDs method */
	
	public int getID()
	{
		return drag.getID();
	} /* end getID method */
	
	public int getType()
	{
		return drag.getType();
	} /* end getType method */
	
	public View view()
	{
		return drag.getView();
	} /* end view method */
	
	public Integer getPivot()
	{
		return drag.getPivot();
	} /* end getPivot method */

	public void setPivot(Integer pivot)
	{
		drag.setPivot(pivot);
	} /* end setPivot method */
	
	public void setEmoteDrawables(Integer[] drawables)
	{
		drag.setEmoteDrawables(drawables);
	} /* end setEmoteDrawables method */
	
	public Integer[] getEmoteDrawables()
	{
		return drag.getEmoteDrawables();
	} /* end getEmoteDrawables method */

	public void setBodyDrawables(Integer[] drawables)
	{
		drag.setBodyDrawables(drawables);
	} /* end setBodyDrawables method */
	
	public Integer[] getBodyDrawables()
	{
		return drag.getBodyDrawables();
	} /* end getBodyDrawables method */
	
	
} /* end DragController class */
