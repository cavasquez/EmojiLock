package com.emojilock.lockscreen.listeners.input;

import java.util.Arrays;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;

import com.emojilock.lockscreen.LockScreen;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.controller.DragData;
import com.emojilock.lockscreen.listeners.TouchListener;
import com.emojilock.lockscreen.metaGrid.MetaInputGrid;

/*****************************************************************************************************
 * InputOnTouchListener will be the listener used by the items inside the input GridView
 *****************************************************************************************************/

public class InputOnTouchListener extends TouchListener
{
	/*************************** Class Methods ***************************/
	public InputOnTouchListener(Controller controller, int position) 
	{
		super(controller, position);
		// TODO Auto-generated constructor stub
	} /* end constructor */

	@Override
	protected boolean down(View view, MotionEvent motionEvent) 
	{// down will start a drag
		// Get the data that will be provided upon drag drop
		controller.drag().set(new DragData(view, LockScreen.VIEW.INPUT, position));
		
		// Form the view that will be dragged
		DragShadowBuilder shadow = new View.DragShadowBuilder(view);
		
		// Make a copy of the drawables and nullify pivot in case a drag over a source is occurring
		controller.drag().setEmoteDrawables(controller.input().getDrawables(MetaInputGrid.EMOTE));
		controller.drag().setBodyDrawables(controller.input().getDrawables(MetaInputGrid.BODY));
		controller.drag().setPivot(null);
		
		/*
		System.out.println("InputOnTouchListener.down: drawables before nivenebt: ");
		System.out.println("InputOnTouchListener.down: size: " + controller.drag().getEmoteDrawables().length);
		System.out.println("InputOnTouchListener.down: E: " + Arrays.toString(controller.drag().getEmoteDrawables()));
		System.out.println("InputOnTouchListener.down: B: " + Arrays.toString(controller.drag().getBodyDrawables()));*/
		
		// Start the drag
		view.startDrag(ClipData.newPlainText("",""), shadow, view, 0);
		view.setVisibility(View.INVISIBLE);
		return true;
	} /* end down method */

	@Override
	protected boolean click(View view, MotionEvent motionEvent) { return true; /* Do nothing */} /* end up method */

	@Override
	protected boolean move(View view, MotionEvent motionEvent) { return true; /* Do nothing */} /* end move method */

} /* end InputOnTouchListener class */
