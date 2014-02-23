package com.hutcservice.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class JudgeLinkInternet {
	 //頁倦選利利大  
    public static boolean IsHaveInternet(final Context context) {  
        try {  
            ConnectivityManager manger = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);  
            NetworkInfo info = manger.getActiveNetworkInfo();  
            return (info!=null && info.isConnected());  
        } catch (Exception e) {  
            return false;  
        }  
    }  
}
