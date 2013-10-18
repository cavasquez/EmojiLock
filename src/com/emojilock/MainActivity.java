package com.emojilock;

import com.emojilock.settings.SettingsActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity 
{
	/*************************** Class Constants ***************************/
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	/*************************** Class Methods ***************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	} /* end onCreate method */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

} /* end MainActivity class */
