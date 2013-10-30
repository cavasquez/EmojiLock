package com.emojilock.lockscreen.listeners.trash;

import java.util.Arrays;

import android.view.DragEvent;
import android.view.View;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.DragListener;
import com.emojilock.lockscreen.metaGrid.MetaInputGrid;

/*****************************************************************************************************
 * OnDragListener for the TrashGrid GridView
 *****************************************************************************************************/

public class TrashGridOnDragListener extends DragListener
{
	/*************************** Class Methods ***************************/
	public TrashGridOnDragListener(Controller controller) 
	{
		super(controller, 0);
	} /* end constructor */
	
	/*************************** Source Listeners ***************************/
	@Override
	protected boolean inputStart(View view, DragEvent event)
	{// Give trash can
		controller.trash().setTrash();
		controller.trash().notifyDataSetChanged();
		view.setBackgroundColor(CLEAR);
		return true;
	} /* end sourceEnter method */
	
	@Override
	protected boolean inputEnter(View view, DragEvent event)
	{// Make red darker
		view.setBackgroundColor(RED);
		return true;
	} /* end sourceExit method */
	
	@Override
	protected boolean inputExit(View view, DragEvent event)
	{// Give back red color
		view.setBackgroundColor(CLEAR);
		return true;
	} /* end sourceExit method */
	
	@Override 
	protected boolean inputDrop(View view, DragEvent event)
	{// Remove the dropped item from the input
		// First, reset the input
		controller.input().setDrawables(MetaInputGrid.BODY, controller.drag().getBodyDrawables());
		controller.input().setDrawables(MetaInputGrid.EMOTE, controller.drag().getEmoteDrawables());
		controller.input().notifyDataSetChanged();
		controller.drag().setPivot(null); // This will ensure that InputOnDragListener does not reset the drawables
		
		// Next, remove the input
		controller.input().remove(controller.drag().getPosition());
		
		// Finally, Reset background and re render
		view.setBackgroundColor(CLEAR);
		controller.input().notifyDataSetChanged();
		
		return true;
	} /* end sourceDrop method */
	
	@Override 
	protected boolean inputEnd(View view, DragEvent event)
	{// Make background clear again and remove trash
		controller.trash().removeImage();
		controller.trash().notifyDataSetChanged();
		view.setBackgroundColor(CLEAR);
		return true;
	} /* end sourceEnd method */
	
} /* end MiscGridOnDragListener class */
