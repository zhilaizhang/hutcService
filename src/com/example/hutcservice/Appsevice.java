package com.example.hutcservice;
import com.hutcservice.common.Config;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class Appsevice extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate(){
		if(Config.sharedPreferences==null){
			Config.sharedPreferences = getSharedPreferences("hutcServiceApp",Context.MODE_PRIVATE);
			
		}
		
	}
}
