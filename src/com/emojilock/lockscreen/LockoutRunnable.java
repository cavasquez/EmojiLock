package com.emojilock.lockscreen;

import com.emojilock.R;
import com.emojilock.lockscreen.listeners.unlock.LockoutPopup;
import com.emojilock.lockscreen.listeners.unlock.LockoutTimer;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Runnable called when locking out the activity
 * @author Carlos
 *
 */
public class LockoutRunnable implements Runnable
{
	/*************************** Class Attributes***************************/
	private View view;
	private long currentTime;
	private long endTime;
	private static LockoutTimer lt;
	
	/*************************** Class Methods ***************************/
	public LockoutRunnable(){ /* Do nothing */} /* end constructor */
	
	/**
	 * Initializes the runnable with the lockout values
	 * @param currentTime	the current time
	 * @param endTime		the end time
	 */
	public void Initialize(View view, long currentTime, long endTime)
	{
		this.view = view;
		this.currentTime = currentTime;
		this.endTime = endTime;
	} /* end Initialize method */

	@Override
	public void run() 
	{
		// Create popup that will warn user about lockout
		LayoutInflater li = (LayoutInflater) this.view.getContext().getSystemService(LockScreen.LAYOUT_INFLATER_SERVICE);
		View popup = li.inflate(R.layout.lockout, null);
		LockoutPopup lp = new LockoutPopup(popup);
		lp.showAtLocation(view, Gravity.CENTER, 0, 0); // Set popup at center
		
		// Create LockoutTimer which will countdown the lockout and dismiss it
		LockoutRunnable.lt = new LockoutTimer(this.endTime - this.currentTime, lp);
		LockoutRunnable.lt.start();
	} /* end run method */
	
	public void interrupt()
	{
		if(LockoutRunnable.lt != null)
		{
			LockoutRunnable.lt.stop();
			LockoutRunnable.lt = null;
		} /* end if */
		
	} /* end interrupt method */
	
	public static void setLockoutTimer(LockoutTimer lt)
	{
		LockoutRunnable.lt = lt;
	} /* end setLockoutRunnable method */
	
} /* end LockoutRunnable class */
