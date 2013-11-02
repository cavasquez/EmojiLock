package com.emojilock.lockscreen.listeners.unlock;

import com.emojilock.R;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * The popup window for the lockout
 * @author Carlos
 *
 */

public class LockoutPopup extends PopupWindow
{	
	/*************************** Class Methods ***************************/
	public LockoutPopup(View popupView)
	{
		super(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		this.setFocusable(true); // Make it so that user cannot interact with lockscreen
		
	} /* end constructor */
	
	/**
	 * Returns the timer textview
	 * @return	timer textview
	 */
	public TextView getTimer()
	{
		TextView text = (TextView) this.getContentView().findViewById(R.id.text_time);
		return text;
	} /* end getTimer method */
	
} /* end LockoutPopup class */
