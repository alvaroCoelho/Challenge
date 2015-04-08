package com.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

//Activity for the splash
public class SplashActivity extends Activity {
	private MyCounter mCounter;
	private Intent mIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		
		Intent i=new Intent(this,MainActivity.class);
		startActivity(i);
			
		mCounter = new MyCounter(1000, 1000);
		mCounter.start();
	}
	private class MyCounter extends CountDownTimer {

		public MyCounter(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			mIntent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(mIntent);
			finish();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
		
		}

	}
}