package com.example.hutcservice;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hutcservice.database.GoodManStuff;
import com.hutcservice.database.QiuGouStuff;
public class QiuGouStuffDetailActivity extends BaseActivity {
	private ImageAdapter adapter;
	private TextView t_title;
	private TextView t_type;
	private TextView t_content;
	private TextView t_phone;
	private TextView t_man;
	private TextView t_QQ;
	private TextView t_time;
	public static QiuGouStuff qiugoustuff = null;
	// 图片的资源ID
	public static List<Bitmap> images = new ArrayList<Bitmap>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qiugoustuffdetail);
		images.clear();
		Intent intentRec = this.getIntent();
		Bundle bundle = intentRec.getExtras();
		String type = bundle.getString("type");
		DisplayMetrics dm = new DisplayMetrics();
		// 取得窗口属性
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		// 窗口的宽度
		float Width = dm.widthPixels;
		int screenWidth = (int) Width;
		View oneCall = findViewById(R.id.btn_oneCall);
		oneCall.getLayoutParams().width = screenWidth / 2;
		View oneShor = findViewById(R.id.btn_oneShortMassage);
		oneShor.getLayoutParams().width = screenWidth / 2;
		t_type = (TextView) findViewById(R.id.goodman_one_type);
		t_title = (TextView) findViewById(R.id.product_one_title);
		t_content = (TextView) findViewById(R.id.product_one_content);
		t_man = (TextView) findViewById(R.id.product_one_linkman);
		t_phone = (TextView) findViewById(R.id.product_one_linkphone);
		t_QQ = (TextView) findViewById(R.id.product_one_linkQQ);
		t_time = (TextView) findViewById(R.id.qiugou_fabu_time);
		t_type.setText(type);
		((ImageView) findViewById(R.id.stuff_image))
				.setImageResource(R.drawable.logo);
		t_title.setText(qiugoustuff.getpTitle());
		t_content.setText(qiugoustuff.getpDetail());
		t_man.setText(qiugoustuff.getlinkMan());
		t_phone.setText(qiugoustuff.getlinkPhone());
		t_QQ.setText(qiugoustuff.getlinkQQ());
		t_time.setText(qiugoustuff.getpTime());
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_oneCall:
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ t_phone.getText().toString().trim()));
			startActivity(intent);
			break;
		case R.id.btn_oneShortMassage:
			Intent intentMes = new Intent();
			intentMes.setType(Intent.ACTION_SENDTO);
			intentMes.setData(Uri.parse("smsto:"
					+ t_phone.getText().toString().trim()));
			startActivity(intentMes);
			break;
		default:
			break;
		}
	}
}
