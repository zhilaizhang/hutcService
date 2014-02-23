package com.example.hutcservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutUs extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);
	}
	public void onClick(View v){
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.aboutus:
			Intent intent =new Intent();
			intent.setClass(AboutUs.this, AboutUsDetail.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			break;
		case R.id.linkus:
			Intent intentLinkUs=new Intent();
			intentLinkUs.setClass(AboutUs.this, LinkUsActivity.class);
			startActivity(intentLinkUs);
			overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			break;
		}
	}
}
 