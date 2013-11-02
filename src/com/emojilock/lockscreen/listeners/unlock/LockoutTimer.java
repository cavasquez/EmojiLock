package com.emojilock.lockscreen.listeners.unlock;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * A CountDownTimer that will count down 
 * 
 * @author Carlos
 */

public class LockoutTimer extends CountDownTimer
{
	/*************************** Class Constants ***************************/
	private static final long COUNTDOWN_TICK = 1000;	// one second
	
	/*************************** Class Attributes ***************************/
	private LockoutPopup popup;			// The UnlockGrid GridView
	private TextView textView;			// The textView
	
	/*************************** Class Methods ***************************/
	public LockoutTimer(long millisInFuture, LockoutPopup popup) 
	{
		super(millisInFuture, COUNTDOWN_TICK);
		this.popup = popup;
		this.textView = popup.getTimer();
		this.textView.setTypeface(Typeface.DEFAULT_BOLD);
	} /* end constructor */

	/**
	 * Dismiss the popup
	 */
	@Override
	public void onFinish() 
	{
		try 
		{
			this.popup.dismiss();
			this.popup = null;
	    } catch (Exception e) {
	        /* do nothing */
	    } /* end catch */
		
	} /* end onFinish method */

	@Override
	public void onTick(long millisUntilFinished) 
	{
		this.textView.setText(formatTime(millisUntilFinished));
	} /* end onTick method */
	
	/**
	 * Set the text of the timer
	 * @param time	the time to be set
	 */
	private String formatTime(long time)
	{
		// Format time
		long seconds = (time / 1000) %60;
		long minutes = (time / (1000*60)) % 60;
		long hours = (time / (1000*60*60)) % 24;
		String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		
		return formattedTime;
		
	} /* end setTimer */

} /* end Lockout class */
