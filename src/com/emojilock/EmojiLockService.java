package com.emojilock;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Create and deploy the LockReciever
 * @author Carlos
 *
 */

public class EmojiLockService extends Service
{
	/*************************** Class Attributes ***************************/
	private BroadcastReceiver receiver;
	
	/*************************** Class Methods ***************************/
	@Override
	public IBinder onBind(Intent intent) 
	{
		/* Do nothing */
		return null;
	} /* end onBind method */
	
	@Override 
	public void onCreate()
	{
		// Create the activity with the following filters and receiver
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_BOOT_COMPLETED);
		
		this.receiver = new LockReceiver();
		this.registerReceiver(this.receiver, filter);
		System.out.println("EmojiLockService.onCreate: EmojiLockService made!");
		
		super.onCreate();
	} /* end onCreate method */
	
	@Override
	public void onDestroy()
	{
		// When the service is destroyed, unregister the broadcast receiver
		this.unregisterReceiver(this.receiver);
		System.out.println("EmojiLockService.onDestroy: EmojiLockService killed!");
		super.onDestroy();
	} /* end onDestroy */
	
} /* end EmojiLockService */
