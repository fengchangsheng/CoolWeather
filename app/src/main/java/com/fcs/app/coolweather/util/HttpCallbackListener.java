package com.fcs.app.coolweather.util;
/**
 * @author Lucare
 *
 * 2015��10��17��
 */
public interface HttpCallbackListener {
	
	void onFinish(String response);
	
	void onError(Exception e);

}
