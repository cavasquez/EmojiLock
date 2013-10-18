package com.emojilock.lockscreen.imageAdapter;

import com.emojilock.lockscreen.controller.Controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/*****************************************************************************************************
 * ImageAdapter will provide some very basic functionality that will be used by SourceView and InputView
 *****************************************************************************************************/

public abstract class ImageAdapter extends BaseAdapter
{
	/*************************** Class Constants ***************************/
	protected static float INVISIBLE = 0;		// An alpha with complete transparency
	protected  static float VISIBLE = 1;		// An alpha with complete visibility
	
	/* Constants for padding */
	protected static final int LEFT = 0;
	protected static final int TOP = 1;
	protected static final int RIGHT = 2;
	protected static final int BOTTOM = 3;
	
	/*************************** Class Attributes ***************************/
	protected static Controller controller;										// The controller
	protected Context context;													// The Context for this adapter
	protected ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;	// The scale type to be used by the GridView. Default to Center Crop
	protected int layoutWidth = 160;											// LayoutWidth of Gridview
	protected int layoutHeight = 160;											// LayoutHeight of GridView
	protected int padding[] = {10, 40, 10, 40};									// Padding to be used by ImageView
	
	/*************************** Abstract Methods ***************************/
	protected abstract ImageView interpret(ImageView imageView, int position);
	
	/*************************** Class Methods ***************************/
	public ImageAdapter(Context context)
	{
		this(context, null);
	} /* end constructor */
	
	public ImageAdapter(Context context, Controller controller)
	{
		this.context = context;
		ImageAdapter.controller = controller;
	} /* end overloaded constructor */
	
	@Override
	public abstract int getCount();

	@Override
	public Object getItem(int position) 
	{
		// TODO Auto-generated method stub
		return null;
	} /* end getItem method */

	@Override
	public long getItemId(int position) 
	{
		// TODO Auto-generated method stub
		return 0;
	} /* end getItem method */
	
	@Override
	public final View getView(int position, View convertView, ViewGroup parent) 
	{
		ImageView imageView = null; // the view that will be returned
		
		// Check to see if convertView is recycled. 
		if(convertView == null)
		{ // convertView is not recycled. Make an image out of it
			imageView = createView();
		} /* end if */
		else
		{
			imageView = (ImageView) convertView;
		} /* end else */
		
		/* 
		 * Here lies the purpose of ImageAdapter. Any class that implements ImageAdapter
		 * will need to define it's own way to treat imageView via the interpret method.
		 */
		return interpret(imageView, position);
	} /* end getView method */
	
	protected ImageView createView()
	{
		ImageView returner = new ImageView(context);
		returner.setLayoutParams(new GridView.LayoutParams(layoutWidth, layoutHeight));
		returner.setScaleType(scaleType);
		returner.setPadding(padding[LEFT], padding[TOP], padding[RIGHT], padding[BOTTOM]);
		
		return returner;
	} /* end createView method */
	
	/*************************** Getters and Setters ***************************/	
	public void setLayoutParams(int width, int height)
	{
		this.layoutWidth = width;
		this.layoutHeight = height;
	} /* end setLayoutParams method */
	
	public void setPadding(int left, int top, int right, int bottom)
	{
		this.padding[LEFT] = left;
		this.padding[TOP] = top;
		this.padding[RIGHT] = right;
		this.padding[BOTTOM] = bottom;
	} /* end setPadding method */
	
} /* end SourceView class */
