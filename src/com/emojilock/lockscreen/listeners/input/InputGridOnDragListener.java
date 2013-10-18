package com.emojilock.lockscreen.listeners.input;

import android.view.DragEvent;
import android.view.View;
import android.widget.GridView;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.DragListener;
import com.emojilock.lockscreen.metaGrid.MetaInputGrid;


/*****************************************************************************************************
 * InputGridOnDragListener will define the OnDragListener to be used by the input GridView
 *****************************************************************************************************/

public class InputGridOnDragListener extends DragListener
{
	/*************************** Class Methods ***************************/
	public InputGridOnDragListener(Controller controller)
	{
		super(controller, 0);
	} /* end constructor */
	
	/*************************** Source Listeners ***************************/
	@Override
	protected boolean sourceEnter(View view, DragEvent event)
	{// Highlight background upon enter of source item
		view.setBackgroundColor(BLUE);
		return true;
	} /* end sourceEnter method */
	
	@Override
	protected boolean sourceExit(View view, DragEvent event)
	{// Remove highlight on background once the source item leaves
		view.setBackgroundColor(CLEAR);
		return true;
	} /* end sourceExit method */
	
	@Override 
	protected boolean sourceDrop(View view, DragEvent event)
	{// Add the dropped item to the front
		// First, check to see if child item did not receive drop first
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		if (((GridView)view).pointToPosition(x, y) ==  GridView.INVALID_POSITION)
		{// Since an item cannot exist in an invalid position, it cannot be that the source was dropped on an item
			// Now, see if the source is an emote or a body and add accordingly
			if(controller.drag().getType() == MetaInputGrid.BODY)
			{
				controller.input().add(null, controller.drag().getID());
			} /* end if */
			else
			{
				controller.input().add(controller.drag().getID(), null);	
			} /* end else */
			
			controller.input().notifyDataSetChanged();
			
		} /* end if */		
		
		// Reset background
		view.setBackgroundColor(CLEAR);
		return true;
	} /* end sourceDrop method */
	
	@Override 
	protected boolean sourceEnd(View view, DragEvent event)
	{
		// Reset background
		view.setBackgroundColor(CLEAR);
		return true;
	} /* end sourceEnd method */
	
} /* end InputGridOnDragListener class */
