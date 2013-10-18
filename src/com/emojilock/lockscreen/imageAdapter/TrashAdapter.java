package com.emojilock.lockscreen.imageAdapter;

import com.emojilock.lockscreen.controller.Controller;

import android.content.Context;
import android.widget.ImageView;

/*****************************************************************************************************
 * TrashAdapter will act as the BaseAdapter for the GridView trashGrid. 
 *****************************************************************************************************/

public class TrashAdapter extends ImageAdapter
{
	/*************************** Class Methods ***************************/
	public TrashAdapter(Context context, Controller controller) 
	{
		super(context, controller);
	} /* end overloaded constructor */

	@Override
	protected ImageView interpret(ImageView imageView, int position) 
	{// Do nothing
		Integer id = controller.trash().getDrawableID(position, null);	// type does not matter
		if (id == null)
		{// In this case, return a blank ImageView
			imageView.setImageResource(controller.source().getDrawableID(0, 0)); // get random image
			imageView.setAlpha(INVISIBLE);
		} /* end if */
		else
		{// Neither emote nor body are null, we must merge drawables
			imageView.setImageResource(id);
			imageView.setAlpha(VISIBLE);
		} /* end else */
		
		// Set listeners
		
		return imageView;
	} /* end ImageView method */

	@Override
	public int getCount() 
	{
		return controller.trash().getSize();
	} /* end getCount method */

} /* end MiscAdapter class */
