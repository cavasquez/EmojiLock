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
	protected static int layoutWidth;											// LayoutWidth of Gridview
	protected static int layoutHeight;											// LayoutHeight of GridView
	
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
		GridView.LayoutParams params = new GridView.LayoutParams(ImageAdapter.layoutWidth, ImageAdapter.layoutHeight);
		returner.setLayoutParams(params);
		returner.setScaleType(scaleType);
		return returner;
	} /* end createView method */
	
	/*************************** Getters and Setters ***************************/	
	public static void setLayoutParams(int width, int height)
	{
		ImageAdapter.layoutWidth = width;
		ImageAdapter.layoutHeight = height;
	} /* end setLayoutParams method */
	
	public static int getLayoutParam()
	{
		return ImageAdapter.layoutHeight;
	} /* end getLayoutParam method */
	
} /* end SourceView class */
