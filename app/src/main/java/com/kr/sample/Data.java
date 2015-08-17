package com.kr.sample;

import java.util.concurrent.Callable;

import kr.ds.asynctask.DsAsyncTask;
import kr.ds.asynctask.DsAsyncTaskCallback;
import kr.ds.httpclient.DsHttpClient;
import android.content.Context;

public class Data {
	private Context mContext;
	private String URL = Config.URL;
	private String PARAM = "";
	private ResultListener mResultListener;

	public Data setCallback(ResultListener listener) {
		mResultListener = listener;
		return this;
	}

	public Data(Context context) {
		mContext = context;
	}
	public Data clear(){
		return this;
	}
	public Data setParam(String param){
		PARAM = param;
		return this;
	}
	
	public Data getView() {

        new DsAsyncTask<String>().setCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
				String content = new DsHttpClient().HttpGet(URL + PARAM,"euc-kr");
				return content;
            }
        }).setCallback(new DsAsyncTaskCallback<String>() {
            @Override
            public void onPreExecute() {
            }

            @Override
            public void onPostExecute(String result) {
				if (!result.matches("") ) {
					if (mResultListener != null) {
						mResultListener.OnComplete(result.trim());
					}
				} else {
					if (mResultListener != null) {
						mResultListener.OnComplete(null);
					}
				}
            }

            @Override
            public void onCancelled() {
            }

            @Override
            public void Exception(Exception e) {
            	if (mResultListener != null) {
            		mResultListener.OnComplete(null);
                }
            }
        }).execute();
        return this;
    }
	
	public interface ResultListener {
        public void OnComplete(String data);
    }

}
