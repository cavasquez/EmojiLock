package com.emojilock.lockscreen;

import com.emojilock.R;
import com.emojilock.lockscreen.controller.Controller;
import com.emojilock.lockscreen.imageAdapter.InputAdapter;
import com.emojilock.lockscreen.imageAdapter.SourceAdapter;
import com.emojilock.lockscreen.imageAdapter.TrashAdapter;
import com.emojilock.lockscreen.imageAdapter.UnlockAdapter;
import com.emojilock.lockscreen.key.KeyChain;
import com.emojilock.lockscreen.listeners.TouchListener;
import com.emojilock.lockscreen.listeners.input.InputGridOnDragListener;
import com.emojilock.lockscreen.listeners.trash.TrashGridOnDragListener;
import com.emojilock.lockscreen.listeners.unlock.UnlockOnTouchListener;
import com.emojilock.lockscreen.metaGrid.MetaInputGrid;
import com.emojilock.lockscreen.metaGrid.MetaSourceGrid;
import com.emojilock.lockscreen.metaGrid.MetaTrashGrid;
import com.emojilock.lockscreen.metaGrid.MetaUnlockGrid;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

/*****************************************************************************************************
 * LockScreen will be the Activity that contains the actual Lock Screen. It 
 * will create the "body" and "emote" pattern, and handle user input. Furthermore,
 * it will respond once the "correct" input has been provided.
 * 
 * LocksScreen will be the "template" for the NewLockScreen and ProductionLockScreen that will 
 * define which locks are created.
 *****************************************************************************************************/

public abstract class LockScreen extends Activity 
{
	/*************************** Class Constants ***************************/
	public static final int EMOTE_COUNT = 8;		// Number of "emotes" being used
	public static final int BODY_COUNT = 8;			// Number of "body"'s being used
	public static final int EMOTE = 1;				// Indicates an emote
	public static final int BODY = 0;				// Indicates a body
	public static enum VIEW {SOURCE, INPUT}		 	// Define the view types
	public static final String PREFERENCE_NAME = "com.enojilock"; // SharedPreference
	
	protected static final boolean ORDERED = true;
	protected static final boolean UNORDERED = false;
	
	/*************************** Class Methods ***************************/
	/************** Abstract Methods **************/
	/**
	 * Make the appropriate key
	 */
	public abstract void makeLock(Controller controller, Activity parent);
	
	/************** Public Methods **************/
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.initialize();
	} /* end onCreate method */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lock_screen, menu);
		return true;
	} /* end onCreateOptionsMenu */	
	
	/**
	 * Create the appropriate key
	 */
	public void makeKey(Controller controller)
	{
		// Check the key type that the user wants to use
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean keyType = pref.getBoolean(getString(R.string.ordering_key), true);
		
		if(keyType == ORDERED) controller.makeKey(KeyChain.Type.ORDERED);
		else controller.makeKey(KeyChain.Type.UNORDERED);
	} /* end makeKey method */
	
	/**
	 * onBackPressed should not allow the user to go back.
	 */
	@Override
	public void onBackPressed() {/* Do nothing */}
	
	/**
	 * Disable volume, power, and home keys (but fail)
	 */
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event)
	{
		boolean returner = false;
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || 
				keyCode == KeyEvent.KEYCODE_POWER ||
				keyCode == KeyEvent.KEYCODE_VOLUME_UP ||
				keyCode == KeyEvent.KEYCODE_CAMERA ||
				keyCode == KeyEvent.KEYCODE_HOME)
		{
			returner = true;
		} /* end if */
		
		return returner;
	} /* end onKeyDown method */
	
	@Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event)
	{
		boolean returner = false;
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || 
				keyCode == KeyEvent.KEYCODE_POWER ||
				keyCode == KeyEvent.KEYCODE_VOLUME_UP ||
				keyCode == KeyEvent.KEYCODE_CAMERA ||
				keyCode == KeyEvent.KEYCODE_HOME)
		{
			returner = true;
		} /* end if */
		
		return returner;
	} /* end onKeyUp method */
	
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		boolean returner = false;
		if(event.getKeyCode() == KeyEvent.KEYCODE_HOME) returner = true;
		return returner;
	} /* end dispatchKeyEvent method */
	
	/**
	 * Initialize the UI and system
	 */
	private void initialize()
	{
		// Some basic GUI stuff
		// Remove the title
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.activity_lock_screen);
		
		// References to the emote images
		int[] emoteDrawableID = {
				R.drawable.emote_0, R.drawable.emote_1,
				R.drawable.emote_2, R.drawable.emote_3,
				R.drawable.emote_4, R.drawable.emote_5,
				R.drawable.emote_6, R.drawable.emote_7
			};
		
		// References to the body images
		int[] bodyDrawableID = {
				R.drawable.body_0, R.drawable.body_1,
				R.drawable.body_2, R.drawable.body_3,
				R.drawable.body_4, R.drawable.body_5,
				R.drawable.body_6, R.drawable.body_7
			};
		
		// Create controller
		Controller controller = new Controller();
		controller.setLockScreen(this);
		
		// Prepare the MetaGrids
		MetaSourceGrid sourceMeta = new MetaSourceGrid(EMOTE_COUNT, BODY_COUNT, emoteDrawableID, bodyDrawableID);
		MetaInputGrid inputMeta = new MetaInputGrid(emoteDrawableID, bodyDrawableID);
		MetaTrashGrid trashMeta = new MetaTrashGrid();
		MetaUnlockGrid unlockMeta = new MetaUnlockGrid();
		
		// Prepare ImageAdapters
		SourceAdapter sourceAdapter = new SourceAdapter(this, controller);
		InputAdapter inputAdapter = new InputAdapter(this, controller);
		TrashAdapter trashAdapter = new TrashAdapter(this, controller);
		UnlockAdapter unlockAdapter = new UnlockAdapter(this, controller);
		
		// Prepare the controller
		controller.setInputController(inputMeta, inputAdapter);
		controller.setSourceController(sourceMeta, sourceAdapter);
		controller.setTrashController(trashMeta, trashAdapter);
		controller.setUnlockController(unlockMeta, unlockAdapter);
				
		// Prepare the GridViews
		GridView sourceGrid = (GridView) findViewById(R.id.sourceGrid);
		sourceGrid.setAdapter(sourceAdapter);
		
		GridView inputGrid = (GridView) findViewById(R.id.inputGrid);
		inputGrid.setAdapter(inputAdapter);		
		inputGrid.setOnDragListener(new InputGridOnDragListener(controller));
		
		GridView trashGrid = (GridView) findViewById(R.id.trashGrid);
		trashGrid.setAdapter(trashAdapter);
		trashGrid.setOnDragListener(new TrashGridOnDragListener(controller));	
		
		GridView unlockGrid = (GridView) findViewById(R.id.unlockGrid);
		unlockGrid.setBackgroundColor(TouchListener.GREEN);
		unlockGrid.setAdapter(unlockAdapter);
		unlockGrid.setOnTouchListener(new UnlockOnTouchListener(controller));
		
		// Make lock
		this.makeLock(controller, this);
		
		// Make key
		this.makeKey(controller);
	} /* end initialize method */

} /* end LockScreen Activity */
