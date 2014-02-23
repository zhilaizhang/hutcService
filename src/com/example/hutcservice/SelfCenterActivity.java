package com.example.hutcservice;

import com.hutcservice.common.Config;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
/*
 个人中心页面*/
public class SelfCenterActivity extends BaseActivity{
	     
	     public void onCreate(Bundle savedInstanceState){
				super.onCreate(savedInstanceState);
				setContentView(R.layout.selfcenter);
				
				
			}
			public void onClick(View v){
				switch(v.getId()){
				case R.id.mysecondhand:							
					Intent it=new Intent(SelfCenterActivity.this,MyTwoHand.class);
					startActivity(it);
					overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);					
					break;
				case R.id.myqiugou:
					Intent intentQiuGou=new Intent();
					intentQiuGou.setClass(SelfCenterActivity.this,MyQiuGouActivity.class);
					startActivity(intentQiuGou);
					overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
					break;
				case R.id.mygoodman:
					Intent intentGoodMan=new Intent();
					intentGoodMan.setClass(SelfCenterActivity.this, MyGoodThingsActivity.class);
					startActivity(intentGoodMan);
					overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
					break;
				case R.id.btn_back:
					finish();
					overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);					
					break;
				case R.id.btn_logout:
					if(Config.User.getFlag()){
						Config.User.setFlag(false);
						Intent intent1=new Intent();
						intent1.setClass(SelfCenterActivity.this, TwoHandMainActivity.class);
						startActivity(intent1);
					}
					overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);					
					break;	
				} 
			}
}
