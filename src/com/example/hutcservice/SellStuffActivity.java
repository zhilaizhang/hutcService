package com.example.hutcservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hutcservice.common.Config;
import com.hutcservice.common.GetStuff;
import com.hutcservice.database.ImageTools;
import com.hutcservice.database.TwoHandCommand;
import com.hutcservice.database.TwoHandStuff;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SellStuffActivity extends BaseActivity {
	private TextView view2;
	private Spinner spinner2;
	private ArrayAdapter adapter2;
	private ImageView photo;
	private OnClickListener imgViewListener;
	private Bitmap bitmap;
	private EditText title; // ����
	private EditText price; // �۸�
	private EditText linkman; // ��ϵ��
	private EditText linkphone; // ��ϵ�˵绰
	private EditText linkqq;
	private EditText content; // ����
	private Button btnPublish;
	private String class1;
	private ProgressDialog progressDialog;
	private TwoHandStuff twohand;
	private Boolean flag; // ����Ƿ񷢲��ɹ�
	private TwoHandCommand tc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sellstuff);
		spinner2 = (Spinner) findViewById(R.id.Spinner01); // ������
		photo = (ImageView) findViewById(R.id.iv_photo1);
		title = (EditText) findViewById(R.id.inputtitle);
		price = (EditText) findViewById(R.id.inputprice);
		linkman = (EditText) findViewById(R.id.inputlinkman);
		linkphone = (EditText) findViewById(R.id.inputlinkphone);
		linkqq = (EditText) findViewById(R.id.inputlinkqq);
		content = (EditText) findViewById(R.id.inputcontent);
		btnPublish = (Button) findViewById(R.id.btn_publish);
		twohand = new TwoHandStuff();
		btnPublish.setOnClickListener(this);
		// ����ѡ������ArrayAdapter��������
		adapter2 = ArrayAdapter.createFromResource(this, R.array.plantes,
				android.R.layout.simple_spinner_item);
		// ���������б�ķ��
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter2 ��ӵ�spinner��
		spinner2.setAdapter(adapter2);
		// ����¼�Spinner�¼�����
		spinner2.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		// ����Ĭ��ֵ
		spinner2.setVisibility(View.VISIBLE);

		imgViewListener = new OnClickListener() {
			public void onClick(View v) {
				final CharSequence[] items = { "���", "����" };
				AlertDialog dlg = new AlertDialog.Builder(
						SellStuffActivity.this).setItems(items,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								// ����item�Ǹ���ѡ��ķ�ʽ��
								// ��items�������涨�������ַ�ʽ�����յ��±�Ϊ1���Ծ͵������շ���
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

	// ʹ��XML��ʽ����
	class SpinnerXMLSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			class1 = adapter2.getItem(arg2).toString();
			// view2.setText("��ѡ��Ҫ�����������ͣ�"+adapter2.getItem(arg2));
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
		if (name.equals("���г�")) {
			class2 = "1";
		} else {
			if (name.equals("��ƿ��")) {
				class2 = "2";
			} else {
				if (name.equals("��������")) {
					class2 = "3";
				} else {
					if (name.equals("������Ʒ")) {
						class2 = "4";
					} else {
						if (name.equals("����")) {
							class2 = "5";
						}
					}
				}
			}
		}
		return class2;
	}

	private void publish() {
		TwoHandCommand tc = new TwoHandCommand(this);

		String title1 = title.getText().toString();
		String price1 = price.getText().toString();
		String linkman1 = linkman.getText().toString();
		String linkphone1 = linkphone.getText().toString();
		String linkqq1 = linkqq.getText().toString();
		String content1 = content.getText().toString();

		if (title1.equals("") || linkman1.equals("")) {
			Toast.makeText(this, "���������ϵ�˲���Ϊ��!", Toast.LENGTH_LONG).show();
		} else {
			if (linkphone1.equals("") && linkqq1.equals("")) {
				Toast.makeText(this, "����������һ����ϵ��ʽ!", Toast.LENGTH_LONG).show();
			} else {
				String uid = Config.User.getName(); // �ӻ��������ȡ�û���
				twohand.setUid(uid);

				twohand.setclassid(class3(class1));
				twohand.setTitle(title1);
				if (price1.equals("")) {
					twohand.setPrice(0);
				} else {
					twohand.setPrice(Integer.parseInt(price1));
				}
				twohand.setLinkman(linkman1);
				twohand.setlinkphone(linkphone1);
				twohand.setlinkqq(linkqq1);
				twohand.setContent(content1);
				twohand.setImage(bitmap);
				publishToWeb();
			}
		}
	}

	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss(); // �ر�ProgressDialog
			if (flag) {
				initAlertDlgSuccess();
			} else {
				initAlertDlgFail();
			}
		}
	};

	private void publishToWeb() {// ͨ�����̴߳��������ͨ��
		tc = new TwoHandCommand(this);
		progressDialog = ProgressDialog.show(SellStuffActivity.this,
				"Loading...", "Please wait...", true, false);
		new Thread() {
			public void run() {
				flag = tc.insert(twohand);
				handler1.sendEmptyMessage(0);
			}
		}.start();
	}

	public void initAlertDlgSuccess() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// ��ʾ��Ϣ
		builder.setMessage("�ٺ�!�����ɹ�")
				.setPositiveButton("����", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentback = new Intent();
						intentback.setClass(SellStuffActivity.this,
								TwoHandMainActivity.class);
						startActivity(intentback);
					}
				}).setNegativeButton("��������", null).create().show();
	}

	public void initAlertDlgFail() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// ��ʾ��Ϣ
		builder.setMessage("����!����ʧ��")
				.setPositiveButton("����", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intentback = new Intent();
						intentback.setClass(SellStuffActivity.this,
								TwoHandMainActivity.class);
						startActivity(intentback);
					}
				}).setNegativeButton("��������", null).create().show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null)
			return;
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
				Log.v("TestFile",
						"SD card is not avaiable/writeable right now.");
				return;
			}
			Bundle bundle = data.getExtras();
			Bitmap photo1 = (Bitmap) bundle.get("data");// ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ

			float SCALE = (float) ((photo1.getWidth() * 1.0) / 200);
			// photo.getByteCount();
			bitmap = ImageTools.zoomBitmap(photo1,
					(int) (photo1.getWidth() / SCALE),
					(int) (photo1.getHeight() / SCALE));

			// �ͷ�ԭʼͼƬռ�õ��ڴ棬��ֹout of memory�쳣����
			photo1.recycle();

			((ImageView) findViewById(R.id.iv_photo1)).setImageBitmap(bitmap);// ��ͼƬ��ʾ��ImageView��
		} else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
			ContentResolver resolver = getContentResolver();
			// ��Ƭ��ԭʼ��Դ��ַ
			Uri originalUri = data.getData();
			try {
				// ʹ��ContentProviderͨ��URI��ȡԭʼͼƬ
				Bitmap photo = MediaStore.Images.Media.getBitmap(resolver,
						originalUri);
				// photo.getByteCount();
				if (photo != null) {
					// Ϊ��ֹԭʼͼƬ�������ڴ��������������Сԭͼ��ʾ��Ȼ���ͷ�ԭʼBitmapռ�õ��ڴ�
					float SCALE = (float) ((photo.getWidth() * 1.0) / 250);
					// photo.getByteCount();
					bitmap = ImageTools.zoomBitmap(photo,
							(int) (photo.getWidth() / SCALE),
							(int) (photo.getHeight() / SCALE));
					// �ͷ�ԭʼͼƬռ�õ��ڴ棬��ֹout of memory�쳣����
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
