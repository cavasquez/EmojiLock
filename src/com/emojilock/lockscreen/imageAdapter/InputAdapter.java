package com.emojilock.lockscreen.imageAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.input.InputOnDragListener;
import com.emojilock.lockscreen.listeners.input.InputOnTouchListener;
import com.emojilock.lockscreen.metaGrid.MetaInputGrid;

/*****************************************************************************************************
 * InputAdapter will act as the BaseAdapter for the Grid View inputGrid. 
 *****************************************************************************************************/

public class InputAdapter extends ImageAdapter
{	
	/*************************** Class Methods ***************************/
	/*************************** Public Methods ***************************/
	public InputAdapter(Context context)
	{
		super(context);
	} /* end constructor */
	
	public InputAdapter(Context context, Controller controller) 
	{
		super(context, controller);
	} /* end overloaded constructor */

	@Override
	protected ImageView interpret(ImageView imageView, int position) 
	{// The ImageView being returned should be a combination (when applicable) of emote and body
		Integer emote = controller.input().getDrawableID(position, MetaInputGrid.EMOTE);
		Integer body = controller.input().getDrawableID(position, MetaInputGrid.BODY);
		
		if (emote == null & body == null)
		{// In this case, return a blank ImageView
			imageView.setImageResource(controller.source().getDrawableID(0, 0)); // get random image
			imageView.setAlpha(INVISIBLE);
		} /* end if */
		else if(emote == null || body == null)
		{// If either emote or body are null, then we do not have to merge drawables.
			if(emote == null) imageView.setImageResource(body);
			else imageView.setImageResource(emote);
			imageView.setAlpha(VISIBLE);
		} /* end else if */
		else
		{// Neither emote nor body are null, we must mrege drawables
			imageView.setImageDrawable(mergeDrawables(body, emote));
			imageView.setAlpha(VISIBLE);
		} /* end else */
		
		// Set listeners
		imageView.setOnDragListener(new InputOnDragListener(controller, position));
		imageView.setOnTouchListener(new InputOnTouchListener(controller, position));
		
		return imageView;
	} /* end ImageView method */
	
	@Override
	public int getCount() 
	{
		return controller.input().getSize();
	} /* end getCount method */
	
	/*************************** Private Methods ***************************/
	private Drawable mergeDrawables(int bodyDrawableID, int emoteDrawableID)
	{ //   Merge two drawables into one. Important, the lower the index, the lower the priority.
		// IE, lower numbers are drawn UNDER higher numbers.
		Drawable[] drawables = new Drawable[2];
		drawables[0] = context.getResources().getDrawable(bodyDrawableID);
		drawables[1] = context.getResources().getDrawable(emoteDrawableID);
		
		return new LayerDrawable(drawables);
	} /* end mergeDrawables method */
	
} /* end IputView class */
