package com.emojilock.lockscreen.listeners.source;

import com.emojilock.lockscreen.LockScreen;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.controller.DragData;
import com.emojilock.lockscreen.listeners.TouchListener;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;

/*****************************************************************************************************
 * SourceOnTouchListener will define the onTouchListener to be used by the ImageView created by the
 * SourceAdapter.
 *****************************************************************************************************/

public class SourceOnTouchListener extends TouchListener
{	
	
	/*************************** Class Attributes ***************************/
	private boolean moved;
	
	/*************************** Class Methods ***************************/
	public SourceOnTouchListener(Controller controller, int position)
	{
		super(controller, position);
	} /* end constructor */
	
	@Override
	protected boolean down(View view, MotionEvent motionEvent)
	{/* Highlight blue */
		moved = false;
		view.setBackgroundColor(BLUE);
		return true;
	} /* end down method */
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
	@Override
	protected boolean click(View view, MotionEvent motionEvent)
	{
		// Reset visibility
		if (view.getVisibility() == View.INVISIBLE) view.setVisibility(View.VISIBLE);
		
		// Remove color
		view.setBackgroundColor(CLEAR);
		
		// Check to see if it was a click. If so, perform onClick
		if (!moved)
		{
			// When the source grid is clicked, it should add the clicked item to the input grid
			controller.input().addInput(controller.source().getID(position, null), controller.source().getType(position));

			// Notify the input adapter of changes so it can re-render
			controller.input().notifyDataSetChanged();
		} /* end if */

		return true;
	} /* end down method */
	
	@Override
	protected boolean move(View view, MotionEvent motionEvent)
	{ // Move will start a drag
		
		// Remove background color
		view.setBackgroundColor(CLEAR);
		moved = true;

		// Get the data that will be provided upon drag drop
		int itemID = controller.source().getID(position, null);
		int type = controller.source().getType(position);
		controller.drag().set(new DragData(view, LockScreen.VIEW.SOURCE, itemID, type));
		
		// Form the view that will be dragged
		DragShadowBuilder shadow = new View.DragShadowBuilder(view);
		
		// Start the drag
		view.startDrag(ClipData.newPlainText("",""), shadow, view, 0);
		return true;
	} /* end down method */
	
} /* end SourceOnTouchListener class */
