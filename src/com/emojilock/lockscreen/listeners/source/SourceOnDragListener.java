package com.emojilock.lockscreen.listeners.source;

import android.view.DragEvent;
import android.view.View;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.DragListener;

/*****************************************************************************************************
 * SourceOnDragListener will define the OnDragListener to be used by the source ImageViews.
 *****************************************************************************************************/

public class SourceOnDragListener extends DragListener
{
	/*************************** Class Methods ***************************/
	public SourceOnDragListener(Controller controller, int position) 
	{
		super(controller, position);
	} /* end constructor */
	
	/*************************** Input Listeners ***************************/
	@Override
	protected boolean inputEnter(View view, DragEvent event)
	{// Highlight background upon enter of source item
		view.setBackgroundColor(BLUE);
		return true;
	} /* end sourceEnter method */
	
	@Override
	protected boolean inputExit(View view, DragEvent event)
	{// Remove highlight on background once the source item leaves
		view.setBackgroundColor(CLEAR);
		return true;
	} /* end sourceExit method */
	
	@Override 
	protected boolean inputDrop(View view, DragEvent event)
	{//replace current type with that of view
		controller.input().replaceInput(controller.source().getID(position, null), controller.source().getType(position), controller.drag().getPosition());
		controller.input().notifyDataSetChanged();
		
		// Remove highlight on background once the source item leaves
		view.setBackgroundColor(CLEAR);
		
		// Make the dragged view visible again
		controller.drag().view().setVisibility(View.VISIBLE);
		return true;
		
	} /* end sourceDrop method */
	
	@Override
	protected boolean inputEnd(View view, DragEvent event)
	{
		// Make the dragged view visible again
		controller.drag().view().setVisibility(View.VISIBLE);
		
		return true;
	} /* end inputEnd method */

} /* end SourceOnDragListener class */
