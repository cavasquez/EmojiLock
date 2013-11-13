package com.emojilock.lockscreen.listeners.unlock;

import java.util.Calendar;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import com.emojilock.R;
import com.emojilock.lockscreen.LockScreen;
import com.emojilock.lockscreen.LockoutRunnable;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.TouchListener;

/*****************************************************************************************************
 * UnlockOnTouchListener will define the OnTouchListener to be used by the unlockGrid GridView.
 *****************************************************************************************************/

public class UnlockOnTouchListener extends TouchListener
{
	/*************************** Class Constants ***************************/
	public static final String LOCKOUT_COUNT_KEY = "com.example.emojilock.lockout_count";
	public static final String LOCKOUT_START_KEY = "com.example.emojilock.lockout_start";
	public static final int LOCKOUT_INTERVAL = 5;		// Number of failed attempts before lockout enabled
	
	/*************************** Class Attributes ***************************/
	private boolean unlocked;
	private SharedPreferences share = null;
	private SharedPreferences.Editor editor;
	private Integer loginFailCount = null;
	
	/*************************** Class Methods ***************************/
	public UnlockOnTouchListener(Controller controller) 
	{
		// ID does not matter
		super(controller, 0);
	} /* end constructor */

	/**
	 * Checks to see if a user is locked out and if the password is correct
	 */
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
		
		if(this.share == null)
		{
			share = PreferenceManager.getDefaultSharedPreferences(view.getContext());
			editor = share.edit();
		} /* end if */
		
		// Get login fail count
		if (this.loginFailCount == null) this.loginFailCount = share.getInt(LOCKOUT_COUNT_KEY, 0);
		
		if(this.unlocked)
		{// User successfully signed in. Clear fail count
			if(LockScreen.isProduction() && this.loginFailCount != 0)
			{
				this.loginFailCount = 0;
				editor.putInt(LOCKOUT_COUNT_KEY, 0);
				editor.commit();
			} /* end if */
			controller.terminate();
		} /* end if */
		else if(LockScreen.isProduction())
		{// User failed in production lock. Increment attempt count and check to see if user is locked out
			// Increment the number of failed logins
			this.loginFailCount++;
			editor.putInt(LOCKOUT_COUNT_KEY, this.loginFailCount);
			editor.commit();

			// Check to see if still locked out
			if(loginFailCount != 0 && loginFailCount % LOCKOUT_INTERVAL == 0)
			{ /* Lock out */
				// Determine lockout start and set it
				Calendar c = Calendar.getInstance();
				long currentTime = c.getTimeInMillis();
				editor.putLong(LOCKOUT_START_KEY, currentTime);
				editor.commit();
				
				// Calculate lockout length and set it
				long lockoutTime = calculateTimeout(this.loginFailCount);
				
				// Create popup that will warn user about lockout
				LayoutInflater li = (LayoutInflater) view.getContext().getSystemService(LockScreen.LAYOUT_INFLATER_SERVICE);
				View popup = li.inflate(R.layout.lockout, null);
				LockoutPopup lp = new LockoutPopup(popup);
				lp.showAtLocation(view, Gravity.CENTER, 0, 0); // Set popup at center

				// Create LockoutTimer which will countdown the lockout and dismiss it
				LockoutTimer lt = new LockoutTimer(lockoutTime, lp);
				LockoutRunnable.setLockoutTimer(lt); // Super important
				lt.start();
				
			} /* end if */
			
		} /* end else */
		
		return false;
	} /* end click method */

	@Override
	protected boolean move(View view, MotionEvent motionEvent) 
	{
		view.setBackgroundColor(GREEN);
		return false;
	} /* end move method */
	
	/**
	 * Calculate the length of time that the lockout should be.
	 * The minimum time will be 60 seconds and quintuple after every
	 * consecutive lockout.
	 * @param loginFailCount	total number of failed 
	 * @return					length of lockout
	 */
	public static final long calculateTimeout(int loginFailCount)
	{
		long returner = 0;
		int lockoutCount = loginFailCount / LOCKOUT_INTERVAL;
		long minimum = 60 * 1000; // 60 seconds
		returner = (long) (minimum * Math.pow(5, lockoutCount - 1));
		return returner;
	} /* end calculateTimeout method */

} /* end UnlockOnTouchListener class */
