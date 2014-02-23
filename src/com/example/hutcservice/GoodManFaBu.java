package com.example.hutcservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.hutcservice.SellStuffActivity.SpinnerXMLSelectedListener;
import com.hutcservice.common.Config;
import com.hutcservice.common.FaBuGoodManToWeb;
import com.hutcservice.common.ImageManage;
import com.hutcservice.database.ImageTools;
import com.hutcservice.database.TwoHandCommand;
import com.hutcservice.database.TwoHandStuff;

public class GoodManFaBu extends BaseActivity {
	private TextView view2;
	private Spinner spinner2;
	private ArrayAdapter adapter2;
	private ImageView photo;
	private OnClickListener imgViewListener;
	private Bitmap bitmap;
	private EditText title; // 标题
	private EditText linkman; // 联系人
	private EditText linkphone; // 联系人电话
	private EditText linkqq;
	private EditText content; // 内容
	private Button btnPublish;
	private String class1;
	private ProgressDialog progressDialog;
	private Boolean flag;
	private String title1;
	private String linkman1;
	private String linkphone1;
	private String linkqq1;
	private String content1;
	private String picture;
	private FaBuGoodManToWeb fbgmtb;
	private String uid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goodmanfabu);
		spinner2 = (Spinner) findViewById(R.id.Spinner01); // 下拉框
		photo = (ImageView) findViewById(R.id.iv_photo1);
		title = (EditText) findViewById(R.id.inputtitle);
		linkman = (EditText) findViewById(R.id.inputlinkman);
		linkphone = (EditText) findViewById(R.id.inputlinkphone);
		linkqq = (EditText) findViewById(R.id.inputlinkqq);
		content = (EditText) findViewById(R.id.inputcontent);
		btnPublish = (Button) findViewById(R.id.btn_publish);
		fbgmtb = new FaBuGoodManToWeb();
		btnPublish.setOnClickListener(this);
		// 将可选内容与ArrayAdapter连接起来
		adapter2 = ArrayAdapter.createFromResource(this, R.array.goodman,
				android.R.layout.simple_spinner_item);
		// 设置下拉列表的风格
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter2 添加到spinner中
		spinner2.setAdapter(adapter2);
		// 添加事件Spinner事件监听
		spinner2.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		// 设置默认值
		spinner2.setVisibility(View.VISIBLE);

		imgViewListener = new OnClickListener() {
			public void onClick(View v) {
				final CharSequence[] items = { "相册", "拍照" };
				AlertDialog dlg = new AlertDialog.Builder(GoodManFaBu.this)
						.setItems(items, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								// 这里item是根据选择的方式，
								// 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
								if (item == 1) {
									try {
										Intent getImageByCamera = new Intent(
												"android.media.action.IMAGE_CAPTURE");
										startActivityForResult(
												getImageByCamera, 1);
									} catch (Exception e) {
										// TODO: handle exception
									}

								} else {
									try {
										Intent getImage = new Intent(
												Intent.ACTION_GET_CONTENT);
										// getImage.addCategory(Intent.CATEGORY_OPENABLE);
										getImage.setType("image/jpeg");

										startActivityForResult(getImage, 2);
									} catch (Exception e) {

									}

								}
							}
						}).create();
				dlg.show();
			}
		};
		photo.setOnClickListener(imgViewListener);

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
		if (name.equals("拾金不昧")) {
			class2 = "1";
		} else {
			if (name.equals("寻物启事")) {
				class2 = "2";
			} else {
				if (name.equals("福利同学")) {
					class2 = "3";
				}
			}
		}
		return class2;
	}

	private void publish() {
		FaBuGoodManToWeb fbgmtb = new FaBuGoodManToWeb();
		ImageManage im = new ImageManage();
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
				if (bitmap == null) {
					picture = "";
				} else {
					picture = im.bitmapToString(bitmap);
				}
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
		progressDialog = ProgressDialog.show(GoodManFaBu.this, "Loading...",
				"Please wait...", true, false);
		new Thread() {
			public void run() {
				try {
					flag = fbgmtb.execute(uid, class3(class1), title1,
							content1, picture, linkman1, linkphone1, linkqq1)
							.get();
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
						intentback.setClass(GoodManFaBu.this,
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
						intentback.setClass(GoodManFaBu.this,
								TwoHandMainActivity.class);
						startActivity(intentback);
					}
				}).setNegativeButton("继续发布", null).create().show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null)
			return;
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
				Log.v("TestFile",
						"SD card is not avaiable/writeable right now.");
				return;
			}
			Bundle bundle = data.getExtras();
			Bitmap photo1 = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

			float SCALE = (float) ((photo1.getWidth() * 1.0) / 200);
			// photo.getByteCount();
			bitmap = ImageTools.zoomBitmap(photo1,
					(int) (photo1.getWidth() / SCALE),
					(int) (photo1.getHeight() / SCALE));

			// 释放原始图片占用的内存，防止out of memory异常发生
			photo1.recycle();

			((ImageView) findViewById(R.id.iv_photo1)).setImageBitmap(bitmap);// 将图片显示在ImageView里
		} else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
			ContentResolver resolver = getContentResolver();
			// 照片的原始资源地址
			Uri originalUri = data.getData();
			try {
				// 使用ContentProvider通过URI获取原始图片
				Bitmap photo = MediaStore.Images.Media.getBitmap(resolver,
						originalUri);

				// photo.getByteCount();
				if (photo != null) {
					// 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存

					float SCALE = (float) ((photo.getWidth() * 1.0) / 250);
					// photo.getByteCount();
					bitmap = ImageTools.zoomBitmap(photo,
							(int) (photo.getWidth() / SCALE),
							(int) (photo.getHeight() / SCALE));
					// 释放原始图片占用的内存，防止out of memory异常发生
					photo.recycle();

					((ImageView) findViewById(R.id.iv_photo1))
							.setImageBitmap(bitmap);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}