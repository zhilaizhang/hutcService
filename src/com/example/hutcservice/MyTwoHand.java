package com.example.hutcservice;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hutcservice.common.Config;
import com.hutcservice.common.GetStuff;
import com.hutcservice.common.JudgeLinkInternet;
import com.hutcservice.database.TwoHandStuff;

public class MyTwoHand extends BaseActivity {

	public List<TwoHandStuff> twostuffs = new ArrayList<TwoHandStuff>();
	public JudgeLinkInternet judgeInter = new JudgeLinkInternet();
	private Boolean flag = false;
	public BaseAdapter adapter = null;
	private String userid = null; // 获取点击的分类，如自行车，电瓶车等
	private ListView pro_list; // 获取显示物品的listview
	private Handler handler;
	private ProgressDialog progressDialog;
	private TextView mytext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mysecondimformation);
		userid = Config.User.getName();
		TextView txtTitle = (TextView) findViewById(R.id.txt_biketitle);
		pro_list = (ListView) findViewById(R.id.product_show_list);
		mytext = (TextView) findViewById(R.id.myText);
		mytext.setVisibility(View.INVISIBLE);
		pro_list.setVisibility(View.INVISIBLE);
		test();
	}

	private Handler handler1 = new Handler() {
		public void handleMessage(Message msg) {
			progressDialog.dismiss();// 关闭ProgressDialog
			bind();// 更新UI
		}
	};

	private void test() {
		progressDialog = ProgressDialog.show(MyTwoHand.this, "Loading...",
				"Please wait...", true, false);
		new Thread() {
			public void run() {
				GetStuff gs = new GetStuff();
				twostuffs = gs.getStuff1(userid);
				handler1.sendEmptyMessage(0);

			}
		}.start();
	}

	private void bind() {
		if (twostuffs == null) {
			mytext.setVisibility(View.VISIBLE);
			pro_list.setVisibility(View.INVISIBLE);
		} else {
			mytext.setVisibility(View.INVISIBLE);
			pro_list.setVisibility(View.VISIBLE);
			adapter = new MyStuffShowAdapter(this, twostuffs);
			pro_list.setAdapter(adapter);
			
		}
	}

}
