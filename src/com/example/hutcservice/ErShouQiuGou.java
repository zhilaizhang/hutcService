package com.example.hutcservice;

import java.util.concurrent.ExecutionException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hutcservice.common.Config;
import com.hutcservice.common.FaBuErShouQiuGouToWeb;
import com.hutcservice.database.QiuGouStuff;
import com.hutcservice.database.TwoHandCommand;
import com.hutcservice.database.TwoHandStuff;

public class ErShouQiuGou extends BaseActivity {
	private TextView view2;
	private Spinner spinner2;
	private ArrayAdapter adapter2;
	private ImageView photo;
	private OnClickListener imgViewListener;
	private EditText title; // 标题
	private EditText linkman; // 联系人
	private EditText linkphone; // 联系人电话
	private EditText linkqq;
	private EditText content; // 内容
	private Button btnPublish;
	private String class1;
	private ProgressDialog progressDialog;
	private FaBuErShouQiuGouToWeb fbesqg;
	private Boolean flag;
	private String title1;
	private String linkman1;
	private String linkphone1;
	private String linkqq1;
	private String content1;
	private String uid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ershouqiugou);
		spinner2 = (Spinner) findViewById(R.id.Spinner01); // 下拉框
		title = (EditText) findViewById(R.id.inputtitle);
		linkman = (EditText) findViewById(R.id.inputlinkman);
		linkphone = (EditText) findViewById(R.id.inputlinkphone);
		linkqq = (EditText) findViewById(R.id.inputlinkqq);
		content = (EditText) findViewById(R.id.inputcontent);
		btnPublish = (Button) findViewById(R.id.btn_publish);
		fbesqg = new FaBuErShouQiuGouToWeb();
		btnPublish.setOnClickListener(this);
		// 将可选内容与ArrayAdapter连接起来
		adapter2 = ArrayAdapter.createFromResource(this, R.array.plantes,
				android.R.layout.simple_spinner_item);
		// 设置下拉列表的风格
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter2 添加到spinner中
		spinner2.setAdapter(adapter2);
		// 添加事件Spinner事件监听
		spinner2.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		// 设置默认值
		spinner2.setVisibility(View.VISIBLE);
	}

	// 使用XML形式操作
	class SpinnerXMLSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			class1 = adapter2.getItem(arg2).toString();
			// view2.setText("请选择要卖东西的类型："+adapter2.getItem(arg2));
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_publish:
			publish();
			break;
		}
	}

	private String class3(String name) {
		String class2 = null;
		if (name.equals("自行车")) {
			class2 = "1";
		} else {
			if (name.equals("电瓶车")) {
				class2 = "2";
			} else {
				if (name.equals("数码世界")) {
					class2 = "3";
				} else {
					if (name.equals("文体用品")) {
						class2 = "4";
					} else {
						if (name.equals("其他")) {
							class2 = "5";
						}
					}
				}
			}
		}
		return class2;
	}

	private void publish() {

		QiuGouStuff qiugoustuff = new QiuGouStuff();
		title1 = title.getText().toString();
		linkman1 = linkman.getText().toString();
		linkphone1 = linkphone.getText().toString();
		linkqq1 = linkqq.getText().toString();
		content1 = content.getText().toString();

		if (title1.equals("") || linkman1.equals("")) {
			Toast.makeText(this, "标题或者联系人不能为空!", Toast.LENGTH_LONG).show();
		} else {
			if (linkphone1.equals("") && linkqq1.equals("")) {
				Toast.makeText(this, "请至少输入一个联系方式!", Toast.LENGTH_LONG).show();
			} else {
				uid = Config.User.getName(); // 从缓存里面获取用户名
				qiugoustuff.setuserId(uid);
				qiugoustuff.setclassId(class3(class1));
				qiugoustuff.setpTitle(title1);
				qiugoustuff.setlinkMan(linkman1);
				qiugoustuff.setlinkPhone(linkphone1);
				qiugoustuff.setlinkQQ(linkqq1);
				qiugoustuff.setpDetail(content1);
				publishToWeb();

			}
		}
	}

	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss(); // 关闭ProgressDialog
			if (flag) {
				initAlertDlgSuccess();
			} else {
				initAlertDlgFail();
			}
		}
	};

	private void publishToWeb() {// 通过子线程处理和网络通信
		progressDialog = ProgressDialog.show(ErShouQiuGou.this, "Loading...",
				"Please wait...", true, false);
		new Thread() {
			public void run() {
				try {
					flag = fbesqg.execute(uid, class3(class1), title1,
							content1, linkman1, linkphone1, linkqq1).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler1.sendEmptyMessage(0);
			}
		}.start();
	}

	public void initAlertDlgSuccess() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// 提示信息
		builder.setMessage("嘿嘿!发布成功")
				.setPositiveButton("返回", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentback = new Intent();
						intentback.setClass(ErShouQiuGou.this,
								TwoHandMainActivity.class);
						startActivity(intentback);
					}
				}).setNegativeButton("继续发布", null).create().show();
	}

	public void initAlertDlgFail() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// 提示信息
		builder.setMessage("呜呜!发布失败")
				.setPositiveButton("放弃", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentback = new Intent();
						intentback.setClass(ErShouQiuGou.this,
								TwoHandMainActivity.class);
						startActivity(intentback);
					}
				}).setNegativeButton("继续发布", null).create().show();
	}

}
