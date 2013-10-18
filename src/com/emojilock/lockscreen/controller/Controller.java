package com.emojilock.lockscreen.controller;

import java.util.ArrayList;
import android.content.SharedPreferences;
import com.emojilock.lockscreen.LockScreen;
import com.emojilock.lockscreen.imageAdapter.InputAdapter;
import com.emojilock.lockscreen.imageAdapter.SourceAdapter;
import com.emojilock.lockscreen.imageAdapter.TrashAdapter;
import com.emojilock.lockscreen.imageAdapter.UnlockAdapter;
import com.emojilock.lockscreen.key.KeyChain;
import com.emojilock.lockscreen.lock.Vault;
import com.emojilock.lockscreen.metaGrid.MetaInputGrid;
import com.emojilock.lockscreen.metaGrid.MetaSourceGrid;
import com.emojilock.lockscreen.metaGrid.MetaTrashGrid;
import com.emojilock.lockscreen.metaGrid.MetaUnlockGrid;

/*****************************************************************************************************
 * Controller will provide access to the various MetaGrids and ImageAdapters as well as the locking
 * mechanisms
 *****************************************************************************************************/

public class Controller 
{
	/*************************** Class Attributes ***************************/
	private LockScreen lockScreen;
	private SourceController source;
	private InputController input;
	private DragController drag;
	private TrashController trash;
	private UnlockController unlock;
	private Vault vault;
	private KeyChain keyChain;
	
	/*************************** Class Methods ***************************/
	public Controller()
	{
		this(null, null, null, null, null, null, null, null);
	} /* end constructor */
	
	public Controller(MetaSourceGrid sourceGrid, SourceAdapter sourceAdapter, MetaTrashGrid trashGrid, 
			MetaInputGrid inputGrid, InputAdapter inputAdapter, TrashAdapter trashAdapter, 
			MetaUnlockGrid unlockGrid, UnlockAdapter unlockAdapter)
	{
		setSourceController(sourceGrid, sourceAdapter);
		setInputController(inputGrid, inputAdapter);
		setTrashController(trashGrid, trashAdapter);
		setUnlockController(unlockGrid, unlockAdapter);
		this.drag = new DragController();
	} /* end overloaded constructor */
	
	/*************************** Getters and Setters ***************************/
	public SourceController source()
	{
		return source;
	} /* end source method */
	
	public InputController input()
	{
		return input;
	} /* end input method */
	
	public DragController drag()
	{
		return this.drag;
	} /* end drag method */
	
	public TrashController trash()
	{
		return this.trash;
	} /* end misc method */
	
	public UnlockController unlock()
	{
		return this.unlock;
	} /* end unlock method */
	
	public Vault vault()
	{
		return this.vault;
	} /* end vault method */
	
	public KeyChain key()
	{
		return this.keyChain;
	} /* end key method */
	
	public boolean unlockPhone()
	{
		ArrayList<Integer>[] password = input.getInput();
		boolean unlocked = false;
		if(vault.unlock(password, this.keyChain.getKey())) unlocked = true;
		return unlocked;
	} /* end unlock method */
	
	public void terminate()
	{
		// End the activity
		lockScreen.finish();
	} /* end terminate method */
	
	public void setLockScreen(LockScreen lockScreen)
	{
		this.lockScreen = lockScreen;
	} /* end setLockScreen method */
	
	public LockScreen getLockScreen()
	{
		return this.lockScreen;
	} /* end getLockScreen method */
	
	public void setSourceController(MetaSourceGrid sourceGrid, SourceAdapter sourceAdapter)
	{
		this.source = new SourceController(sourceGrid, sourceAdapter);
	} /* end setSourceController method */
	
	public void setInputController(MetaInputGrid inputGrid, InputAdapter inputAdapter)
	{
		this.input = new InputController(inputGrid, inputAdapter);
	} /* end setInputContorller class */	
	
	public void setTrashController(MetaTrashGrid trashGrid, TrashAdapter trashAdapter)
	{
		this.trash = new TrashController(trashGrid, trashAdapter);
	} /* end setInputContorller class */
	
	public void setUnlockController(MetaUnlockGrid unlockGrid, UnlockAdapter unlockAdapter)
	{
		this.unlock = new UnlockController(unlockGrid, unlockAdapter);
	} /* end setUnlockController class */
	
	public void makeVault(Vault.Type type, SharedPreferences share)
	{
		this.vault = new Vault(type, this, share);
	} /* end setVault method */
	
	public void makeKey(KeyChain.Type type)
	{
		this.keyChain = new KeyChain(type);
	} /* end makeKey method */
	
} /* end Controller class */
