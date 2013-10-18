package com.emojilock.lockscreen.listeners;

import com.emojilock.lockscreen.controller.Controller;

import android.widget.AdapterView.OnItemClickListener;

/*****************************************************************************************************
 * ItemClickListener will define some of the basics for the OnItemClickListeners used
 *****************************************************************************************************/

public abstract class ItemClickListener implements OnItemClickListener
{
	/*************************** Class Attributes ***************************/
	protected static Controller controller;			// The controller
	
	/*************************** Class Methods ***************************/
	protected ItemClickListener(Controller controller)
	{
		ItemClickListener.controller = controller;
	} /* end SourceOnItemClickListener constructor */
	
} /* end ItemClickListener */
