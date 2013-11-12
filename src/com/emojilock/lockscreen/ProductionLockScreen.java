package com.emojilock.lockscreen;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import com.emojilock.R;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.unlock.LockoutPopup;
import com.emojilock.lockscreen.listeners.unlock.LockoutTimer;
import com.emojilock.lockscreen.listeners.unlock.UnlockOnTouchListener;
import com.emojilock.lockscreen.lock.Vault;

/**
 * ProductionLockScreen will create a Real lock that checks for correct input
 * 
 * @author Carlos
 */

public class ProductionLockScreen extends LockScreen
{
	/*************************** Class Methods ***************************/
	private LockoutRunnable lockout;
	public static LockoutTimer lt;
	
	/*************************** Class Methods ***************************/
	@Override
	protected void onStart()
	{
		super.onStart();
		this.lockout = new LockoutRunnable();
		
	}/* end onResume */
	
	@Override
	public void makeLock(Controller controller, Activity parent) 
	{
		// Make the real lock
		SharedPreferences pref = parent.getSharedPreferences(LockScreen.PREFERENCE_NAME, Context.MODE_PRIVATE);
		controller.makeVault(Vault.Type.REAL, pref);
		LockScreen.production = true;
		
	} /* end makeLock method */
	
	/**
	 * Check for lockout and perform lockout if necessarry
	 */
	@Override
	public void onResume()
	{
		super.onResume();
		this.checkLockoutStatus();
	} /* end onResume method */
	
	/**
	 * Checks if lockout is running. If it is, kills it
	 */
	@Override
	public void onPause()
	{
		super.onPause();

		if(this.lockout != null)
		{
			View view = this.findViewById(R.id.sourceGrid);
			this.lockout.interrupt();
			view.removeCallbacks(this.lockout);
			
		} /* end if */

	} /* end onPause method */
	
	/**
	 * Checks to see if u locked out. If so, lockout the user
	 */
	private void checkLockoutStatus()
	{
		SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
		int loginFailCount = share.getInt(UnlockOnTouchListener.LOCKOUT_COUNT_KEY, 0);
		long start = share.getLong(UnlockOnTouchListener.LOCKOUT_START_KEY, 0);
		if( (loginFailCount != 0) && (loginFailCount % UnlockOnTouchListener.LOCKOUT_INTERVAL == 0) )
		{// User is still locked out.
			// Calculate total lockout time
			long lockoutTime = UnlockOnTouchListener.calculateTimeout(loginFailCount);
			
			// Calculate end time
			long endTime = lockoutTime + start; 
			
			// Find out if user is still locked out
			Calendar c = Calendar.getInstance();
			long currentTime = c.getTimeInMillis();
			if (currentTime < endTime)
			{// User is locked out. Lock user out
				this.lockout(currentTime, endTime);
			} /* end if */
			
		} /* end if */
		
	} /* end checkLockoutStatus method */
	
	/**
	 * Lockout the user based on the remaining time
	 */
	private void lockout(long currentTime, long endTime)
	{
		// Create popup that will warn user about lockout
		View view = this.findViewById(R.id.sourceGrid);
		this.lockout.Initialize(view, currentTime, endTime);
		view.post(this.lockout);
	} /* end lockout method */
	
} /* end ProductionLockScreen class */
