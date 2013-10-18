package com.emojilock.lockscreen;

import com.emojilock.lockscreen.controller.Controller;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * LockScreenPhoneListener will define the phoneStateListener to be used by LockScreen.
 * It should terminate the LockScreen activity when the phone is wringing or is off hook.
 * This should only be used in the ProductionLockScreen
 * @author Carlos
 *
 */

public class LockScreenPhoneStateListener extends PhoneStateListener
{
	/*************************** Class Attributes ***************************/
	private Controller controller;
	
	/*************************** Class Methods ***************************/
	public LockScreenPhoneStateListener(Controller controller)
	{
		this.controller = controller;
	} /* end constructor */
	
	/**
	 * Deactivate the LockScreen when ringing or off hook
	 */
	@Override
	public void onCallStateChanged(int state, String incomingNumber)
	{
		switch(state)
		{
			case TelephonyManager.CALL_STATE_RINGING:
				controller.terminate();
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				controller.terminate();
				break;
			default:
				break;
		} /* end switch */
	
	} /* end onCallStateChanged method */
	
} /* end LockScreenPhoneListener class */
