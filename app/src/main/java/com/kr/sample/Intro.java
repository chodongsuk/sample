package com.kr.sample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Intro extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		
		Handler handler = new Handler(){
        	public void handleMessage(Message msg){
        		super.handleMessage(msg);
        		startActivity(new Intent(getApplicationContext(),MainActivity.class));
        		finish();
        	}
        };
		 handler.sendEmptyMessageDelayed(0, 3000);
	}
}
