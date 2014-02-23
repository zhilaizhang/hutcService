package com.example.hutcservice;

import java.util.ArrayList;
import java.util.List;

import com.hutcservice.common.Config;
import com.hutcservice.common.GetStuff;
import com.hutcservice.database.GoodManStuff;
import com.hutcservice.database.QiuGouStuff;
import com.hutcservice.database.TwoHandStuff;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyQiuGouActivity extends Activity {

	private	List<QiuGouStuff> qiugoustuffs = new ArrayList<QiuGouStuff>();
    private	BaseAdapter adapter = null;
    private	String userid = null; // 获取点击的分类，如自行车，电瓶车等
    private ListView pro_list; // 获取显示物品的listview
	private ProgressDialog progressDialog;
	private TextView mytext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myqiugouimformation);
		userid = Config.User.getName();
		pro_list = (ListView) findViewById(R.id.product_show_list);
		mytext=(TextView)findViewById(R.id.myText);
		mytext.setVisibility(View.INVISIBLE);
		pro_list.setVisibility(View.INVISIBLE);
		test();
	}

	private Handler handler1 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// 关闭ProgressDialog
			progressDialog.dismiss();
			// 更新UI
			bind();
		}
	};

	private void test() {
		progressDialog = ProgressDialog.show(MyQiuGouActivity.this,
				"Loading...", "Please wait...", true, false);
		new Thread() {
			public void run() {
				GetStuff gs = new GetStuff();
				qiugoustuffs = gs.getQiuGou(userid);
				handler1.sendEmptyMessage(0);
			}
		}.start();
	}

	private void bind() {
		// TODO Auto-generated method stub
		if (qiugoustuffs == null) {
			mytext=(TextView)findViewById(R.id.myText);
			mytext.setVisibility(View.VISIBLE);
			pro_list.setVisibility(View.INVISIBLE);
		} else {
			mytext.setVisibility(View.INVISIBLE);
			pro_list.setVisibility(View.VISIBLE);
			adapter = new QiuGouShowAdapter(this, qiugoustuffs);
			pro_list.setAdapter(adapter);
			pro_list.setLayoutAnimation(new LayoutAnimationController(
					AnimationUtils.loadAnimation(this, android.R.anim.fade_in)));
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		}
	}
}
