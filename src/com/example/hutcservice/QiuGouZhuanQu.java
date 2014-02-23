package com.example.hutcservice;

import java.util.ArrayList;
import java.util.List;

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

import com.hutcservice.common.Config;
import com.hutcservice.common.GetStuff;
import com.hutcservice.database.QiuGouStuff;

public class QiuGouZhuanQu extends Activity {

	private List<QiuGouStuff> qiugoustuffs = new ArrayList<QiuGouStuff>();
	private BaseAdapter adapter = null;
	private ListView pro_list; // ��ȡ��ʾ��Ʒ��listview
	private ProgressDialog progressDialog;
	private String type1;
	private TextView show_nothing = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qiugouzhuanqu);
		pro_list = (ListView) findViewById(R.id.qiugou_show_list);
		show_nothing = (TextView) findViewById(R.id.show_nothing);
		show_nothing.setVisibility(View.INVISIBLE);
		pro_list.setVisibility(View.INVISIBLE);
		test();
	}

	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// �ر�ProgressDialog
			progressDialog.dismiss();
			// ����UI
			bind();
		}
	};

	private void test() {
		progressDialog = ProgressDialog.show(QiuGouZhuanQu.this, "Loading...",
				"Please wait...", true, false);
		new Thread() {
			public void run() {
				GetStuff gs = new GetStuff();
				qiugoustuffs = gs.getQiuGouAll();
				handler1.sendEmptyMessage(0);
			}
		}.start();
	}

	private void bind() {
		// TODO Auto-generated method stub
		if (qiugoustuffs == null) {
			
			show_nothing.setVisibility(View.VISIBLE);
			pro_list.setVisibility(View.INVISIBLE);
		} else {
			show_nothing.setVisibility(View.INVISIBLE);
			pro_list.setVisibility(View.VISIBLE);
			adapter = new QiuGouShowAllAdapter(this, qiugoustuffs);
			pro_list.setAdapter(adapter);
			pro_list.setLayoutAnimation(new LayoutAnimationController(
					AnimationUtils.loadAnimation(this, android.R.anim.fade_in)));
			pro_list.setOnItemClickListener(new ListView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					if (qiugoustuffs.get(position) != null) {

						if (!String.valueOf(
								(qiugoustuffs.get(position)).getgId()).equals(
								"")
								&& String.valueOf((qiugoustuffs.get(position))
										.getgId()) != null) {
							/* newһ��Intent���󣬲�ָ��class */
							Intent intent = new Intent(QiuGouZhuanQu.this,
									QiuGouStuffDetailActivity.class);
							String type = qiugoustuffs.get(position)
									.getclassId();

							if (type.equals("1")) {
								type1 = "���г�";
							}
							if (type.equals("2")) {
								type1 = "��ƿ��";
							}
							if (type.equals("3")) {
								type1 = "��������";
							}
							if (type.equals("4")) {
								type1 = "������Ʒ";
							}
							if (type.equals("5")) {
								type1 = "������Ʒ";
							}

							Bundle bundle = new Bundle();
							bundle.putString("type", type1);
							intent.putExtras(bundle);
							QiuGouStuffDetailActivity.qiugoustuff = qiugoustuffs
									.get(position);

							QiuGouZhuanQu.this.startActivity(intent);
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

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		}
	}
}