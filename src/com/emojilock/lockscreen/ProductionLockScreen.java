package com.emojilock.lockscreen;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import com.emojilock.R;
import com.emojilock.lockscreen.controller.Controller;
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
	@Override
	protected void onStart()
	{
		super.onStart();
		this.checkLockoutStatus();
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
		view.post(new LockoutRunnable(view, currentTime, endTime));
		
	} /* end lockout method */
	
} /* end ProductionLockScreen class */
