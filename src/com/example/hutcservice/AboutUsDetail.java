package com.example.hutcservice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


public class AboutUsDetail extends BaseActivity {
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutusdetail);
		
	}
	public void onclick(View v){
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_toPhone:
			String t_phone="18767221151";
			Intent intentPhone=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+t_phone.toString().trim() ));
			 startActivity(intentPhone); 
			 overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			 break;
		case R.id.btn_toNet:
			Intent intenNet=new Intent();
			intenNet.setClass(AboutUsDetail.this, AboutUsNet.class);
			startActivity(intenNet);
			overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			 break; 
		}
	}
}
