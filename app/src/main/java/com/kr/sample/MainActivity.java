package com.kr.sample;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kr.sample.Data.ResultListener;


public class MainActivity extends Activity implements OnClickListener{
	private BackPressCloseHandler backPressCloseHandler = new BackPressCloseHandler(this);
	private LinearLayout mLinearLayoutUpdate;
	private ImageView mImageViewPercent, mImageViewState;
	private TextView  mTextViewTime;
	private String mPercent, mTime;
	public class BackPressCloseHandler {

		private long backKeyPressedTime = 0;
		private Toast toast;
		private Activity activity;

		public BackPressCloseHandler(Activity context) {
			this.activity = context;
		}
		public void onBackPressed() {
			if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
				backKeyPressedTime = System.currentTimeMillis();
				showGuide();
				return;
			}
			if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
				activity.finish();
				toast.cancel();
			}
		}
		private void showGuide() {
			toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		(mLinearLayoutUpdate = (LinearLayout)findViewById(R.id.linearLayout_update)).setOnClickListener(this);
		mTextViewTime = (TextView)findViewById(R.id.textView_time);
		mImageViewPercent = (ImageView)findViewById(R.id.imageView_percent);
		mImageViewState = (ImageView)findViewById(R.id.imageView_state);
		
		
		mTextViewTime.setText("데이터 받는 중입니다...");
		new Data(getApplicationContext()).clear().setParam("").setCallback(new ResultListener() {
			@Override
			public void OnComplete(String data) {
				// TODO Auto-generated method stub
				if(data != null){
					try{
						String[] datas = data.split("/");
						if(datas.length > 1){
							mPercent = datas[0];
							mTime = datas[1];
							mTextViewTime.setText(mTime);
							setPercent(Integer.parseInt(mPercent));
							if(Integer.parseInt(mPercent) < 40){
								Toast.makeText(getApplicationContext(), "부족 합니다.",Toast.LENGTH_SHORT).show();
							}
						}
					}catch(Exception e){
						mTextViewTime.setText("");
						Toast.makeText(getApplicationContext(), "서버 측 확인 부탁드립니다.",Toast.LENGTH_SHORT).show();
					}
				}else{
					mTextViewTime.setText("");
				}
			}
		}).getView();
	}
	
	public void setPercent(int percent){
		if(percent >= 0 && percent < 10){
			mImageViewPercent.setBackgroundResource(R.drawable._40);
			mImageViewState.setBackgroundResource(R.drawable.off);
		}else if(percent > 10 && percent < 20){
			mImageViewPercent.setBackgroundResource(R.drawable._40);
			mImageViewState.setBackgroundResource(R.drawable.off);
		}else if(percent > 20 && percent < 20){
			mImageViewPercent.setBackgroundResource(R.drawable._40);
			mImageViewState.setBackgroundResource(R.drawable.off);
		}else if(percent > 30 && percent < 40){
			mImageViewPercent.setBackgroundResource(R.drawable._40);
			mImageViewState.setBackgroundResource(R.drawable.off);
		}else if(percent > 40 && percent < 50){
			mImageViewPercent.setBackgroundResource(R.drawable._100);
			mImageViewState.setBackgroundResource(R.drawable.on);
		}else if(percent > 50 && percent < 60){
			mImageViewPercent.setBackgroundResource(R.drawable._100);
			mImageViewState.setBackgroundResource(R.drawable.on);
		}else if(percent > 60 && percent < 70){
			mImageViewPercent.setBackgroundResource(R.drawable._100);
			mImageViewState.setBackgroundResource(R.drawable.on);
		}else if(percent > 70 && percent < 80){
			mImageViewPercent.setBackgroundResource(R.drawable._100);
			mImageViewState.setBackgroundResource(R.drawable.on);
		}else if(percent > 80 && percent < 90){
			mImageViewPercent.setBackgroundResource(R.drawable._100);
			mImageViewState.setBackgroundResource(R.drawable.on);
		}else if(percent > 90 && percent <= 100){
			mImageViewPercent.setBackgroundResource(R.drawable._100);
			mImageViewState.setBackgroundResource(R.drawable.on);
		}
	}
	
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			if (KeyCode == KeyEvent.KEYCODE_BACK) {
				backPressCloseHandler.onBackPressed();
				return true;
			}
		}
		return super.onKeyDown(KeyCode, event);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.linearLayout_update:
			mTextViewTime.setText("데이터 받는 중입니다...");
			new Data(getApplicationContext()).clear().setParam("").setCallback(new ResultListener() {
				@Override
				public void OnComplete(String data) {
					// TODO Auto-generated method stub
					if(data != null){
						try{
							String[] datas = data.split("/");
							if(datas.length > 1){
								mPercent = datas[0];
								mTime = datas[1];
								mTextViewTime.setText(mTime);
								setPercent(Integer.parseInt(mPercent));
								if(Integer.parseInt(mPercent) < 40){
									Toast.makeText(getApplicationContext(), "부족 합니다.",Toast.LENGTH_SHORT).show();
								}
							}
						}catch(Exception e){
							mTextViewTime.setText("");
							Toast.makeText(getApplicationContext(), "서버 측 확인 부탁드립니다.",Toast.LENGTH_SHORT).show();
						}
						
					}else{
						mTextViewTime.setText("");
					}
				}
			}).getView();
			break;
		}
	}
}