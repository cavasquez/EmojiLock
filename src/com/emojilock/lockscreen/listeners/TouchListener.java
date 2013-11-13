package com.emojilock.lockscreen.listeners;

import java.lang.Math;
import com.emojilock.lockscreen.controller.Controller;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/*****************************************************************************************************
 * TouchLIstener will be a custom OnTouchListener utilized by the lock screen 
 *****************************************************************************************************/

public abstract class TouchListener implements OnTouchListener
{
	/*************************** Class Constants ***************************/
	public static final int CLEAR = 0x00000000;		// A clear color
	public static final int BLUE = 0x5524EAE7;		// A tealish color with some transparency	
	public static final int GREEN = 0x5509BA19;		// A green color
	public  static final int RED = 0x55D00F0F;		// A Red color with some transparency
	private static final int CLICK_DEVIATION = 25;	// The max change for an action to be considered a click
	
	/*************************** Class Attributes ***************************/
	protected int position;							// The position of the item being touched.
	protected static Controller controller;			// The controller
	private int startX;								// Initial x position
	private int startY;								// Initial y position
	private boolean drag = false;					// Drag flag
	
	/*************************** Class Methods ***************************/
	public TouchListener(Controller controller, int position)
	{
		this.position = position;
		TouchListener.controller = controller;
	} /* end constructor */
	
	@Override
	public final boolean onTouch(View view, MotionEvent motionEvent) 
	{
		boolean returner = true;
		switch(motionEvent.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				this.drag = false;
				startX = (int)motionEvent.getRawX();
				startY = (int)motionEvent.getRawY();
				returner = down(view, motionEvent);
				break;
			case MotionEvent.ACTION_UP:
				if(!drag) returner = click(view, motionEvent);
				break;
				
			case MotionEvent.ACTION_MOVE:
				if(!wasClick((int)motionEvent.getRawX(), (int)motionEvent.getRawY()))
				{
					this.drag = true;
					returner = move(view, motionEvent);
				} /* end if */
				break;
			default:
				break;
		} /* end switch */
		
		return returner;
	} /* end onTouch method */
	
	
	private boolean wasClick(int x, int y)
	{
		boolean returner = false;
		if (Math.abs(startX - x) <= CLICK_DEVIATION && Math.abs(startY - y) <= CLICK_DEVIATION) returner = true;
		return returner;
	} /* end wasClick method */
	
	/* The methods below will be utilized by the classes that extend TouchListener */
	protected abstract boolean down(View view, MotionEvent motionEvent);
	protected abstract boolean click(View view, MotionEvent motionEvent);
	protected abstract boolean move(View view, MotionEvent motionEvent);
	
} /* end TouchLIstener class */
