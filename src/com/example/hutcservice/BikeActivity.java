package com.example.hutcservice;

import com.hutcservice.common.GetStuff;
import com.hutcservice.database.*;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
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

public class BikeActivity extends BaseActivity {

	private List<TwoHandStuff> twostuffs = new ArrayList<TwoHandStuff>();
	private BaseAdapter adapter = null;
	private String classid = null; // 获取点击的分类，如自行车，电瓶车等
	private ListView pro_list = null; // 获取显示物品的listview
	private ProgressDialog progressDialog = null;
	private TextView shownothing = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bike);
		Bundle bundle = getIntent().getExtras();
		classid = bundle.getString("cid");
		TextView txtTitle = (TextView) findViewById(R.id.txt_biketitle);
		shownothing = (TextView) findViewById(R.id.show_nothing);
		pro_list = (ListView) findViewById(R.id.product_show_list);
		shownothing.setVisibility(View.INVISIBLE);
		pro_list.setVisibility(View.INVISIBLE);
		if (classid.equals("1")) {
			txtTitle.setText("自行车");
		}
		if (classid.equals("2")) {
			txtTitle.setText("电瓶车");
		}
		if (classid.equals("3")) {
			txtTitle.setText("数码世界");
		}
		if (classid.equals("4")) {
			txtTitle.setText("文体用品");
		}
		if (classid.equals("5")) {
			txtTitle.setText("生活用品");
		}
		test();
	}

	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();// 关闭ProgressDialog
			bind();// 更新UI
		}
	};

	private void test() {
		progressDialog = ProgressDialog.show(BikeActivity.this, "Loading...",
				"Please wait...", true, false);
		new Thread() {
			public void run() {
				GetStuff gs = new GetStuff();
				twostuffs = gs.getBike(classid);
				handler1.sendEmptyMessage(0);
			}
		}.start();
	}

	private void bind() {
		if (twostuffs == null) {
			shownothing.setVisibility(View.VISIBLE);
			pro_list.setVisibility(View.INVISIBLE);
		} else {
			shownothing.setVisibility(View.INVISIBLE);
			pro_list.setVisibility(View.VISIBLE);
			adapter = new MyStuffShowAdapter(this, twostuffs);
			pro_list.setAdapter(adapter);
			pro_list.setLayoutAnimation(new LayoutAnimationController(
					AnimationUtils.loadAnimation(this, android.R.anim.fade_in)));
			pro_list.setOnItemClickListener(new ListView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					if (twostuffs.get(position) != null) {

						if (!String.valueOf((twostuffs.get(position)).getId())
								.equals("")
								&& String.valueOf((twostuffs.get(position))
										.getId()) != null) {
							/* new一个Intent对象，并指定class */
							Intent intent = new Intent(BikeActivity.this,
									TwoStuffDetailActivity.class);
							TwoStuffDetailActivity.twostuff = twostuffs
									.get(position);

							BikeActivity.this.startActivity(intent);
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
