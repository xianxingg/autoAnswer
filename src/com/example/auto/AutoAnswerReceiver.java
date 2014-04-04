package com.example.auto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

public class AutoAnswerReceiver extends BroadcastReceiver {
	@Override
	
	//收到来电广播后，会触发该函数。
	public void onReceive(Context context, Intent intent) {
			//开启一个Service 转到AutoAnswerIntentService，尝试两种方法接听电话
			context.startService(new Intent(context, AutoAnswerIntentService.class));
			Log.d("AutoAnswer startService","Error1");	
			//直接调用模拟按键方法接听电话。
			//answerPhoneHeadsethook(context);
			Log.d("answerPhoneead","Error2");	
	}		
	

	private void answerPhoneHeadsethook(Context context) {
		// Simulate a press of the headset button to pick up the call
		Intent buttonDown = new Intent(Intent.ACTION_MEDIA_BUTTON);		
		buttonDown.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK));
		context.sendOrderedBroadcast(buttonDown, "android.permission.CALL_PRIVILEGED");

		// froyo and beyond trigger on buttonUp instead of buttonDown
		Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);		
		buttonUp.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
		context.sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");
	}
}

