package com.emojilock.lockscreen.imageAdapter;

import com.emojilock.lockscreen.controller.Controller;

import android.content.Context;
import android.widget.ImageView;

/*****************************************************************************************************
 * UnlockAdapter will act as the BaseAdapter for the GridView unlockGrid. 
 *****************************************************************************************************/

public class UnlockAdapter extends ImageAdapter
{
	/*************************** Class Methods ***************************/
	public UnlockAdapter(Context context, Controller controller) 
	{
		super(context, controller);
	} /* end constructor */

	@Override
	protected ImageView interpret(ImageView imageView, int position) 
	{
		Integer id = controller.unlock().getDrawableID(position, null);	// type does not matter
		imageView.setImageResource(id);
		/* Check to see if phone has time out */
		if(controller.unlock().isLocked())
		{// Phone is locked, do not display padlock icon
			imageView.setAlpha(INVISIBLE);
		} /* end if */
		else
		{// Phone is not locked, display padlock icon
			
			imageView.setAlpha(VISIBLE);
		} /* end else */
		
		return imageView;
	} /* end ImageView method */

	@Override
	public int getCount() 
	{
		return controller.unlock().getSize();
	} /* end getCount method */
	
} /* end UnlockAdapter class */
