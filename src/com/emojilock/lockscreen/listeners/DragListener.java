package com.emojilock.lockscreen.listeners;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

import com.emojilock.lockscreen.LockScreen;
import com.emojilock.lockscreen.controller.Controller;

/*****************************************************************************************************
 * IDragListener will provide a basis for the DragListeners that will be implemented by this project.
 *****************************************************************************************************/

public abstract class DragListener implements OnDragListener
{
	/*************************** Class Attributes ***************************/
	protected static final int CLEAR = 0x00000000;	// A clear color
	protected static final int BLUE = 0x7724EAE7;	// A tealish color with some transparency		
	protected static final int RED = 0x77D00F0F;	// A redish color with some transparency	
	
	/*************************** Class Attributes ***************************/
	protected static Controller controller;			// The controller
	protected int position;							// The views position in the input GridView
	
	/*************************** Class Methods ***************************/
	protected DragListener(Controller controller, int position)
	{
		DragListener.controller = controller;
		this.position = position;
	} /* end constructor */
	
	@Override
	public final boolean onDrag(View view, DragEvent event) 
	{
		boolean returner = true;
		if (controller.drag().getViewType() == LockScreen.VIEW.INPUT)
		{// An input item is being dragged
			switch (event.getAction()) 
			  {
				case DragEvent.ACTION_DRAG_STARTED:
					returner = inputStart(view, event);
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					returner = inputEnter(view, event);
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					returner = inputExit(view, event);
					break;
				case DragEvent.ACTION_DROP:
					returner = inputDrop(view, event);
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					returner = inputEnd(view, event);
					break;
				case DragEvent.ACTION_DRAG_LOCATION:
					break;
				default:
					break;
			  } /* end switch */
		} /* end if */
		else
		{// A source item is being dragged
			switch (event.getAction()) 
		      {
		      	case DragEvent.ACTION_DRAG_STARTED:
		      		returner = sourceStart(view, event);
			        break;
			      case DragEvent.ACTION_DRAG_ENTERED:
			    	  returner = sourceEnter(view, event);
			        break;
			      case DragEvent.ACTION_DRAG_EXITED:
			    	  returner = sourceExit(view, event);
			        break;
			      case DragEvent.ACTION_DROP:
			    	  returner = sourceDrop(view, event);
			        break;
			      case DragEvent.ACTION_DRAG_ENDED:
			    	  returner = sourceEnd(view, event);
			    	  break;
			      default:
			    	  break;
		      } /* end switch */
		} /* end else */
	      return returner;
	} /* end onDrag method */

	/*************************** Input Methods ***************************/
	protected boolean inputStart(View view, DragEvent event) {return true; /* Default to nothing */} /* end inputStart */
	
	protected boolean inputEnter(View view, DragEvent event) { return true; /* Default to nothing */} /* end inputEnter */
	
	protected boolean inputExit(View view, DragEvent event) {return true; /* Default to nothing */} /* end inputExit */
	
	protected boolean inputDrop(View view, DragEvent event) {return true; /* Default to nothing */} /* end inputDrop */
	
	protected boolean inputEnd(View view, DragEvent event) {return true; /* Default to nothing */} /* end inputEnd */
	
	/*************************** Source Methods ***************************/
	protected boolean sourceStart(View view, DragEvent event) {return true; /* Default to nothing */} /* end sourceStart */
	
	protected boolean sourceEnter(View view, DragEvent event) {return true; /* Default to nothing */} /* end sourceEnter */
	
	protected boolean sourceExit(View view, DragEvent event) {return true; /* Default to nothing */} /* end sourceExit */
	
	protected boolean sourceDrop(View view, DragEvent event) 
	{
		view.setVisibility(View.VISIBLE);
		return true;
	} /* end sourceDrop */
	
	protected boolean sourceEnd(View view, DragEvent event) 
	{
		view.setVisibility(View.VISIBLE);
		return true;
	} /* end sourceEnd */
	
} /* end DragListener */
