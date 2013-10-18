package com.emojilock;

import com.emojilock.lockscreen.ProductionLockScreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Check for screen on, screen off, and power on activities and start the appropriate intent.
 * @author Carlos
 *
 */

public class LockReceiver extends BroadcastReceiver 
{
	/*************************** Class Methods ***************************/
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		// This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF) || 
				intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
		{
			System.out.println("LockReceiver.onReceive: getting a screen off or action boot completed");
			// Start the ProductionLockScreen activity if the phone boots up 
			// or the phone's screen turns off
			Intent newIntent = new Intent(context, ProductionLockScreen.class);
			newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			// Add flags to ensure only one instance of ProductionServer is ever running
			newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			context.startActivity(newIntent);
		} /* end if */

	} /* end onRecieve method */
	
} /* end LockReciever class */
