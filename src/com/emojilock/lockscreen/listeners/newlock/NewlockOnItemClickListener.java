package com.emojilock.lockscreen.listeners.newlock;

import android.view.View;
import android.widget.AdapterView;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.ItemClickListener;

/**
 * NewlockOnItemClickListener will set the new password for the lock
 * screen when clicked.
 * @author Carlos
 *
 */

public class NewlockOnItemClickListener extends ItemClickListener
{
	/*************************** Class Methods ***************************/
	protected NewlockOnItemClickListener(Controller controller) 
	{
		super(controller);
		// TODO Auto-generated constructor stub
	} /* end constructor */

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	{
		// TODO Auto-generated method stub
		
	} /* end onItemClick method */
	
} /* end NewlockOnItemClickListener */
