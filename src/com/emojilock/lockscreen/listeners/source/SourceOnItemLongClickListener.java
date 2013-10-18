package com.emojilock.lockscreen.listeners.source;

import com.emojilock.lockscreen.LockScreen;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.controller.DragData;

import android.content.ClipData;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

/*****************************************************************************************************
 * SourceOnItemLongClickListener will define the OnItemLongClickListener to be used by the source GridView 
 *****************************************************************************************************/

public class SourceOnItemLongClickListener implements OnItemLongClickListener 
{
	/*************************** Class Attributes ***************************/
	private Controller controller;			// The controller
	
	/*************************** Class Methods ***************************/
	public SourceOnItemLongClickListener(Controller controller)
	{
		this.controller = controller;
	} /* end SourceOnItemClickListener constructor */

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) 
	{// onItemLongClick will start a drag
		// Get the data that will be provided upon drag drop
		int itemID = controller.source().getID(position, null);
		int type = controller.source().getType(position);
		controller.drag().set(new DragData(view, LockScreen.VIEW.SOURCE, itemID, type));
		
		// Form the view that will be dragged
		DragShadowBuilder shadow = new View.DragShadowBuilder(view);
		
		// Start the drag
		view.startDrag(ClipData.newPlainText("",""), shadow, view, 0);
		return true;
	} /* end onItemLongClick method */

} /* end SourceOnItemLongClickListener */
