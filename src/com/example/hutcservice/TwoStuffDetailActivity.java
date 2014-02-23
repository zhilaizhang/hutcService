package com.example.hutcservice;

import java.util.ArrayList;
import java.util.List;

import com.hutcservice.database.*;




import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TwoStuffDetailActivity extends BaseActivity{
	private ImageAdapter adapter;
	private TextView t_title;
	private TextView t_price;
	private TextView t_content;
	private TextView t_phone;
	private TextView t_man;
	private TextView t_QQ;
	private TextView t_time;
	public static TwoHandStuff twostuff = null;
	// 图片的资源ID
	public static List<Bitmap> images = new ArrayList<Bitmap>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stuff_detail);
		images.clear();
		
		DisplayMetrics dm = new DisplayMetrics();
		// 取得窗口属性
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		// 窗口的宽度
		 float Width = dm.widthPixels;
		 int screenWidth = (int) Width;
		
		View oneCall=findViewById(R.id.btn_oneCall);
		oneCall.getLayoutParams().width=screenWidth/2;
		View oneShor=findViewById(R.id.btn_oneShortMassage);
		oneShor.getLayoutParams().width=screenWidth/2;
		
		t_title=(TextView)findViewById(R.id.product_one_title);
		t_price=(TextView)findViewById(R.id.product_one_price);
		t_content=(TextView)findViewById(R.id.product_one_content);
		t_man=(TextView)findViewById(R.id.product_one_linkman);
		t_phone=(TextView)findViewById(R.id.product_one_linkphone);
		t_QQ=(TextView)findViewById(R.id.product_one_linkQQ);
		t_time=(TextView)findViewById(R.id.product_one_time1);
		((ImageView) findViewById(R.id.stuff_image)).setImageBitmap(twostuff.getImage());
		//images.add(twostuff.getImage());
		t_title.setText(twostuff.getTitle());
		t_price.setText(String.valueOf(twostuff.getPrice()));
		t_content.setText(twostuff.getContent());
		t_man.setText(twostuff.getLinkman());
		t_phone.setText(twostuff.getlinkphone());
		t_QQ.setText(twostuff.getlinkqq());
		t_time.setText(twostuff.gettime());
		}
	 public void onClick(View v){
		 switch(v.getId()){
		 case R.id.btn_back:
			 finish();
			 break;
		
		 case R.id.btn_oneCall:
			 Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+t_phone.getText().toString().trim() ));
			 startActivity(intent);  break;
		 case R.id.btn_oneShortMassage:
			 Intent intentMes = new Intent();
			 intentMes.setType(Intent.ACTION_SENDTO);
			 intentMes.setData(Uri.parse("smsto:"+t_phone.getText().toString().trim()));
			 startActivity(intentMes);
			 break;
			 default : break;
		 }
	 }
}
