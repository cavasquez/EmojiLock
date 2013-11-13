package com.emojilock.lockscreen.imageAdapter;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.source.SourceOnDragListener;
import com.emojilock.lockscreen.listeners.source.SourceOnTouchListener;

import android.content.Context;
import android.widget.ImageView;

/*****************************************************************************************************
 * SourceAdapter will be the BaseAdapter for sourceGrid
 *****************************************************************************************************/

public class SourceAdapter extends ImageAdapter
{
	/*************************** Class Attributes ***************************/
	public SourceAdapter(Context context) 
	{
		this(context, null);

	} /* end constructor */
	
	public SourceAdapter(Context context, Controller controller)
	{
		super(context, controller);
	} /* end overloaded constructor */
	
	@Override
	protected ImageView interpret(ImageView imageView, int position)
	{
		imageView.setImageResource(controller.source().getDrawableID(position, null));	// The type does not matter
		
		// Set listeners
		imageView.setOnDragListener(new SourceOnDragListener(controller, position));
		imageView.setOnTouchListener(new SourceOnTouchListener(controller, position));
		
		return imageView;
	} /* end ImageView method */

	@Override
	public int getCount() 
	{
		return controller.source().getSize();
	} /* end getCount method */

} /* end SourceAdapter class */
