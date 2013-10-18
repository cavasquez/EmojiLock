package com.emojilock.lockscreen.listeners.source;

import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.listeners.ItemClickListener;

import android.view.View;
import android.widget.AdapterView;

/*****************************************************************************************************
 * SourceOnItemClickListener will define the OnItemClickListener to be used by the source GridView 
 *****************************************************************************************************/

public class SourceOnItemClickListener extends ItemClickListener
{
	/*************************** Class Methods ***************************/
	public SourceOnItemClickListener(Controller controller)
	{
		super(controller);
	} /* end SourceOnItemClickListener constructor */
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	{
		// When the source grid is clicked, it should add the clicked item to the input grid
		controller.input().addInput(controller.source().getID(position, null), controller.source().getType(position));

		// Notify the input adapter of changes so it can re-render
		controller.input().notifyDataSetChanged();
		
	} /* end onItemClick method */
	
} /* end sourceOnItemClickListener class */
