package com.emojilock.lockscreen.listeners.input;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.DragListener;
import com.emojilock.lockscreen.metaGrid.MetaInputGrid;

import android.view.DragEvent;
import android.view.View;

/*****************************************************************************************************
 * InputOnDragListener will define the OnDragListener to be used by the input ImageViews.
 *****************************************************************************************************/

public class InputOnDragListener extends DragListener
{	
	/*************************** Class Methods ***************************/
	public InputOnDragListener(Controller controller, int position)
	{
		super(controller, position);
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
	{//replace current type with that of view
		controller.input().replaceInput(controller.drag().getID(), controller.drag().getType(), position);
		controller.input().notifyDataSetChanged();
		
		// Reset background
		view.setBackgroundColor(CLEAR);
		return false;
	} /* end sourceDrop method */
	
	@Override 
	protected boolean sourceEnd(View view, DragEvent event)
	{// Allow the gridview end to kick in
		return true;
	} /* end sourceEnd method */
	
	/*************************** Input Listeners ***************************/
	@Override
	protected boolean inputEnter(View view, DragEvent event)
	{
		// Make sure there is a pivot. If not, then this is the first pivot
		if(controller.drag().getPivot() == null)
		{// The first pivot simply needs to set it's value as a pivot and add itself. Also, remove the initial item
			controller.drag().setPivot(position);
			controller.drag().view().setVisibility(View.VISIBLE);
			controller.input().addDrawable(null, null, controller.drag().getPosition()); // Add new one first
			controller.input().removeDrawable(controller.drag().getPosition() + 1); // The "old" "item" will now be in the next position over
			
			// Now, check to see if the current "item" is not the dragged item
			if(position != controller.drag().getPosition())
			{// If this is not the original item, place an empty image there
				controller.input().removeDrawable(controller.drag().getPosition()); // Removal must happen first!
				controller.input().addDrawable(null, null, position);
			} /* end if */
			
			// Finally, re-render the grid
			controller.input().notifyDataSetChanged();
			
		} /* end if */
		
		// If this is not the first pivot, then make sure that we are not at the same pivot again
		else if(controller.drag().getPivot() != position)
		{
			// First, remove the previous pivot (this must happen first!)
			controller.input().removeDrawable(controller.drag().getPivot());
			
			// Second, set this as the new pivot and shift the drawables accordingly
			controller.drag().setPivot(position);
			controller.input().addDrawable(null, null, position);
			
			// Finally, re-render the grid
			controller.input().notifyDataSetChanged();
		} /* end else if */
		
		return true;
		
	} /* end sourceEnter method */
	
	@Override 
	protected boolean inputDrop(View view, DragEvent event)
	{// This is where the "item" will be moved to
		// Reset the drawables
		controller.input().setDrawables(MetaInputGrid.EMOTE, controller.drag().getEmoteDrawables());
		controller.input().setDrawables(MetaInputGrid.BODY, controller.drag().getBodyDrawables());
		
		// Get the values of the "item" to be moved
		Integer emoteID = controller.input().getID(controller.drag().getPosition(), MetaInputGrid.EMOTE);
		Integer bodyID = controller.input().getID(controller.drag().getPosition(), MetaInputGrid.BODY);
		
		// Remove the dragged item from it "old" position
		controller.input().remove(controller.drag().getPosition());
		
		// Place the "item" in its new location
		controller.input().add(emoteID, bodyID, position);
		
		// Finally, re-render the grid and nullify pivot
		controller.input().notifyDataSetChanged();
		controller.drag().setPivot(null);
		return true;
		
	} /* end sourceDrop method */
	
	@Override 
	protected boolean inputEnd(View view, DragEvent event)
	{// If the "item" was not dropped, reset the drawables
		if(controller.drag().getPivot() != null)
		{// Recall that drag nullifies pivot. Hence, if the pivot is not null, it must not have been dropped
			controller.input().setDrawables(MetaInputGrid.BODY, controller.drag().getBodyDrawables());
			controller.input().setDrawables(MetaInputGrid.EMOTE, controller.drag().getEmoteDrawables());
			controller.input().notifyDataSetChanged();
		} /* end if */
		
		return true;
		
	} /* end sourceDrop method */

} /* end InputOnDragListener class */
