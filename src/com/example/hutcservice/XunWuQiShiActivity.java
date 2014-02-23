package com.example.hutcservice;

import java.util.ArrayList;
import java.util.List;

import com.hutcservice.common.GetStuff;
import com.hutcservice.database.GoodManStuff;

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

public class XunWuQiShiActivity extends Activity {

	private List<GoodManStuff> goodmanstuffs = new ArrayList<GoodManStuff>();
	private BaseAdapter adapter = null;
	private ListView pro_list; // 获取显示物品的listview
	private ProgressDialog progressDialog;
	private TextView showNothing = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goodman);
		pro_list = (ListView) findViewById(R.id.goodman_show_list);
		showNothing = (TextView) findViewById(R.id.show_nothing);
		showNothing.setVisibility(View.INVISIBLE);
		pro_list.setVisibility(View.INVISIBLE);
		test();
	}

	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss(); // 关闭ProgressDialog
			bind(); // 更新UI
		}
	};

	private void test() {
		progressDialog = ProgressDialog.show(XunWuQiShiActivity.this,
				"Loading...", "Please wait...", true, false);
		new Thread() {
			public void run() {
				GetStuff gs = new GetStuff();
				goodmanstuffs = gs.getXunWuQiShiStuff();
				handler1.sendEmptyMessage(0);
			}
		}.start();
	}

	private void bind() {
		if (goodmanstuffs == null) {
			showNothing.setVisibility(View.VISIBLE);
			pro_list.setVisibility(View.INVISIBLE);
		} else {
			showNothing.setVisibility(View.INVISIBLE);
			pro_list.setVisibility(View.VISIBLE);
			adapter = new GoodManShowAdapter(this, goodmanstuffs);
			pro_list.setAdapter(adapter);
			pro_list.setLayoutAnimation(new LayoutAnimationController(
					AnimationUtils.loadAnimation(this, android.R.anim.fade_in)));
			pro_list.setOnItemClickListener(new ListView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					if (goodmanstuffs.get(position) != null) {

						if (!String.valueOf(
								(goodmanstuffs.get(position)).getgId()).equals(
								"")
								&& String.valueOf((goodmanstuffs.get(position))
										.getgId()) != null) {
							/* new一个Intent对象，并指定class */
							Intent intent = new Intent(XunWuQiShiActivity.this,
									GoodManStuffDetailActivity.class);
							Bundle bundle = new Bundle();
							bundle.putString("type", "寻物启事");
							intent.putExtras(bundle);
							GoodManStuffDetailActivity.twostuff = goodmanstuffs
									.get(position);

							XunWuQiShiActivity.this.startActivity(intent);
							overridePendingTransition(
									android.R.anim.slide_in_left,
									android.R.anim.slide_out_right);
						} else {

						}
					}

				}

			});
		}
	}
}
