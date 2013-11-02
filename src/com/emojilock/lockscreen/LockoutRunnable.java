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
	
	/*************************** Class Methods ***************************/
	public LockoutRunnable(View view, long currentTime, long endTime)
	{
		this.view = view;
		this.currentTime = currentTime;
		this.endTime = endTime;
	} /* end constructor */

	@Override
	public void run() 
	{
		// Create popup that will warn user about lockout
		LayoutInflater li = (LayoutInflater) this.view.getContext().getSystemService(LockScreen.LAYOUT_INFLATER_SERVICE);
		View popup = li.inflate(R.layout.lockout, null);
		LockoutPopup lp = new LockoutPopup(popup);
		lp.showAtLocation(view, Gravity.CENTER, 0, 0); // Set popup at center

		// Create LockoutTimer which will countdown the lockout and dismiss it
		LockoutTimer lt = new LockoutTimer(this.endTime - this.currentTime, lp);
		lt.start();
		
	} /* end run method */
	
} /* end LockoutRunnable class */
