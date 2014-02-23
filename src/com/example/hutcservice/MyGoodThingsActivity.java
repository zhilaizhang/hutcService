package com.example.hutcservice;

import java.util.ArrayList;
import java.util.List;

import com.hutcservice.common.Config;
import com.hutcservice.common.GetStuff;
import com.hutcservice.database.GoodManStuff;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyGoodThingsActivity extends BaseActivity {
	private List<GoodManStuff> shijinbumeistuffs = new ArrayList<GoodManStuff>();
	private List<GoodManStuff> xunwuqishistuffs = new ArrayList<GoodManStuff>();
	private List<GoodManStuff> fulitongxuestuffs = new ArrayList<GoodManStuff>();
	private BaseAdapter adapter = null;
	private ListView shijinbumei_list; // 显示物拾金不昧物品的listview
	private ListView xunwuqishi_list; // 显示寻物启事的listview
	private ListView fulitongxue_list; // 显示福利同学物品的listview
	private ProgressDialog progressDialog;
	private TextView showNothing1;
	private TextView showNothing2;
	private TextView showNothing3;
	private String uid = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mygoodthing);
		shijinbumei_list = (ListView) findViewById(R.id.shijinbumei_show_list);
		xunwuqishi_list = (ListView) findViewById(R.id.xunwuqishi_show_list);
		fulitongxue_list = (ListView) findViewById(R.id.fulitongxue_show_list);
		showNothing1 = (TextView) findViewById(R.id.myText1);
		showNothing2 = (TextView) findViewById(R.id.myText2);
		showNothing3 = (TextView) findViewById(R.id.myText3);
		uid = Config.User.getName();
		test();
	}

	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// 关闭ProgressDialog
			progressDialog.dismiss();
			// 更新UI
			bindShiJinBuMei();
			bindXunWuQiShi();
			bindFuLiTongXue();
		}
	};

	private void test() {
		progressDialog = ProgressDialog.show(MyGoodThingsActivity.this,
				"Loading...", "Please wait...", true, false);
		new Thread() {
			public void run() {
				GetStuff gs = new GetStuff();
				shijinbumeistuffs = gs.getShiJinBuMeiStuffByUid(uid);
				xunwuqishistuffs = gs.getXunWuQiShiStuffByUid(uid);
				fulitongxuestuffs = gs.getFuLiTongXueStuffByUid(uid);
				handler1.sendEmptyMessage(0);
			}
		}.start();
	}

	private void bindShiJinBuMei() {
		if (shijinbumeistuffs == null) {
			showNothing1.setVisibility(View.VISIBLE);
			shijinbumei_list.setVisibility(View.INVISIBLE);
		} else {
			showNothing1.setVisibility(View.INVISIBLE);
			shijinbumei_list.setVisibility(View.VISIBLE);
			adapter = new GoodManShowAdapter(this, shijinbumeistuffs);
			shijinbumei_list.setAdapter(adapter);
		}
	}

	private void bindXunWuQiShi() {
		if (xunwuqishistuffs == null) {
			showNothing2.setVisibility(View.VISIBLE);
			xunwuqishi_list.setVisibility(View.INVISIBLE);
		} else {
			showNothing2.setVisibility(View.INVISIBLE);
			xunwuqishi_list.setVisibility(View.VISIBLE);
			adapter = new GoodManShowAdapter(this, xunwuqishistuffs);
			xunwuqishi_list.setAdapter(adapter);
		}
	}

	private void bindFuLiTongXue() {
		if (fulitongxuestuffs == null) {
			showNothing3.setVisibility(View.VISIBLE);
			fulitongxue_list.setVisibility(View.INVISIBLE);
		} else {
			showNothing3.setVisibility(View.INVISIBLE);
			fulitongxue_list.setVisibility(View.VISIBLE);
			adapter = new GoodManShowAdapter(this, fulitongxuestuffs);
			fulitongxue_list.setAdapter(adapter);

		}
	}
}