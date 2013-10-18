package com.emojilock.lockscreen.listeners.unlock;

import android.view.MotionEvent;
import android.view.View;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.TouchListener;

/*****************************************************************************************************
 * UnlockOnTouchListener will define the OnTouchListener to be used by the unlockGrid GridView 
 *****************************************************************************************************/

public class UnlockOnTouchListener extends TouchListener
{
	/*************************** Class Attributes ***************************/
	private boolean unlocked;
	
	/*************************** Class Methods ***************************/
	public UnlockOnTouchListener(Controller controller) 
	{
		// ID does not matter
		super(controller, 0);
	} /* end constructor */

	@Override
	protected boolean down(View view, MotionEvent motionEvent) 
	{
		// Check if phone gets unlocked and signal user accordingly
		this.unlocked = controller.unlockPhone();
		if(!this.unlocked) view.setBackgroundColor(RED);
		return false;
	} /* end down method */

	@Override
	protected boolean click(View view, MotionEvent motionEvent) 
	{
		// Reset color background and unlock if input is correct
		view.setBackgroundColor(GREEN);
		if(this.unlocked) controller.terminate();
		return false;
	} /* end click method */

	@Override
	protected boolean move(View view, MotionEvent motionEvent) 
	{
		/* do nothing */
		return false;
	} /* end move method */

} /* end UnlockOnTouchListener class */
