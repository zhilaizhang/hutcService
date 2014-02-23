package com.example.hutcservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.hutcservice.common.Config;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class TwoHandMainActivity extends BaseActivity {

	private ViewPager viewPager; // android-support-v4�еĻ������
	private List<View> views;// Tabҳ���б�
	private int currentItem = 0; // ��ǰͼƬ��������
	private ScheduledExecutorService scheduledExecutorService;
	private View view1, view2, view3, view4;
	private int screenHeight;
	private float density;
	private boolean isContinue = true;
	private PopupWindow popup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twohandmain);
		Intent intent = new Intent(this, Appsevice.class);
		startService(intent);
		DisplayMetrics dm = new DisplayMetrics();
		// ȡ�ô�������
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		Button more = (Button) findViewById(R.id.btn_fabu);
		density = dm.density;
		float Height = dm.heightPixels;
		screenHeight = (int) Height;

		View top = findViewById(R.id.topbar);
		top.getLayoutParams().height = screenHeight / 12;
		View foot = findViewById(R.id.foot);
		foot.getLayoutParams().height = screenHeight / 13;

		View re_1 = findViewById(R.id.one_row_one);
		re_1.getLayoutParams().height = (int) (screenHeight / 5.2);
		re_1.getLayoutParams().width = screenHeight / 3;

		View re_12 = findViewById(R.id.one_row_two);
		re_12.getLayoutParams().height = (int) (screenHeight / 5.2);
		re_12.getLayoutParams().width = screenHeight / 3;

		View re_21 = findViewById(R.id.two_row_one);
		re_21.getLayoutParams().height = (int) (screenHeight / 5.2);
		re_21.getLayoutParams().width = screenHeight / 3;

		View re_22 = findViewById(R.id.two_row_two);
		re_22.getLayoutParams().height = (int) (screenHeight / 5.2);
		re_22.getLayoutParams().width = screenHeight / 3;

		View re_31 = findViewById(R.id.three_row_one);
		re_31.getLayoutParams().height = (int) (screenHeight / 5.2);
		re_31.getLayoutParams().width = screenHeight / 3;

		View re_32 = findViewById(R.id.three_row_two);
		re_32.getLayoutParams().height = (int) (screenHeight / 5.2);
		re_32.getLayoutParams().width = screenHeight / 3;

		View re_max = findViewById(R.id.max_rela);
		re_max.getLayoutParams().height = (int) (screenHeight / 5.2);

		// �������ڿ�ʼ
		LayoutInflater inflater1 = LayoutInflater.from(this);
		// ���봰�������ļ�
		View view = inflater1.inflate(R.layout.popupwindow, null);
		// ����PopupWindow����
		popup = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);
		// ��Ҫ����һ�´˲����������߿���ʧ
		popup.setBackgroundDrawable(new BitmapDrawable());
		// ���õ��������ߴ�����ʧ
		popup.setOutsideTouchable(true);
		// ���ô˲�����ý��㣬�����޷����
		popup.setFocusable(true);

		// �����ťmore�����µĲ�
		more.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (popup.isShowing()) {
					// ���ش��ڣ���������˵��������Сʱ������Ҫ�˷�ʽ����
					popup.dismiss();
				} else {
					// ��ʾ����
					popup.showAsDropDown(v);
				}
			}
		});
		// �������ڽ���

		// ��ҳ����
		viewPager = (ViewPager) findViewById(R.id.vp);
		views = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(this);
		view1 = inflater.inflate(R.layout.index_1, null);
		view2 = inflater.inflate(R.layout.index_2, null);
		view3 = inflater.inflate(R.layout.index_2, null);
		view4 = inflater.inflate(R.layout.index_2, null);
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		TextView text_title = (TextView) view1.findViewById(R.id.index_1_title);
		text_title.setText("ʦԺ��Ϣ���񿪲�����www.kuoyisa.cn");
		ImageView img2 = (ImageView) view2.findViewById(R.id.viewpager_image);
		ImageView img3 = (ImageView) view3.findViewById(R.id.viewpager_image);
		ImageView img4 = (ImageView) view4.findViewById(R.id.viewpager_image);
		img2.setImageResource(R.drawable.viewpage);
		img3.setImageResource(R.drawable.goodthing);
		img4.setImageResource(R.drawable.fabu);

		viewPager.setAdapter(new MyViewPagerAdapter(views));// �������ViewPagerҳ���������
		// ����һ������������ViewPager�е�ҳ��ı�ʱ����
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		viewPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_qiugouzhuanqu:
			Intent intentqiugouzhuanqu = new Intent();
			intentqiugouzhuanqu.setClass(TwoHandMainActivity.this,
					QiuGouZhuanQu.class);
			startActivity(intentqiugouzhuanqu);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.btn_back:
			finish();
			break;
		case R.id.popup_ershoufabu: // ���ַ���
			Intent intent2 = new Intent();
			if (!Config.User.getFlag()) {

				intent2.setClass(TwoHandMainActivity.this, LoginActivity.class);
			} else {
				intent2.setClass(TwoHandMainActivity.this,
						SellStuffActivity.class);
			}
			startActivity(intent2);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.popup_ershouqiugou: // ������
			Intent intent3 = new Intent();
			if (!Config.User.getFlag()) {

				intent3.setClass(TwoHandMainActivity.this, LoginActivity.class);
			} else {
				intent3.setClass(TwoHandMainActivity.this, ErShouQiuGou.class);
			}
			startActivity(intent3);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.popup_fulitongxue: // ����ͬѧ
			Intent intent4 = new Intent();
			if (!Config.User.getFlag()) {

				intent4.setClass(TwoHandMainActivity.this, LoginActivity.class);
			} else {
				intent4.setClass(TwoHandMainActivity.this, GoodManFaBu.class);
			}
			startActivity(intent4);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.popup_shijinbumei: // ʰ����
			Intent intent5 = new Intent();
			if (!Config.User.getFlag()) {

				intent5.setClass(TwoHandMainActivity.this, LoginActivity.class);
			} else {
				intent5.setClass(TwoHandMainActivity.this, GoodManFaBu.class);
			}
			startActivity(intent5);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.popup_xunwuqishi: // Ѱ������
			Intent intent6 = new Intent();
			if (!Config.User.getFlag()) {

				intent6.setClass(TwoHandMainActivity.this, LoginActivity.class);
			} else {
				intent6.setClass(TwoHandMainActivity.this, GoodManFaBu.class);
			}
			startActivity(intent6);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.one_row_one:
			Intent intent = new Intent();
			intent.setClass(TwoHandMainActivity.this, BikeActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("cid", "1");
			intent.putExtras(bundle);
			startActivity(intent);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.btn_personal:
			if (Config.User.getFlag()) {
				Intent intent1 = new Intent();
				intent1.setClass(TwoHandMainActivity.this,
						SelfCenterActivity.class);
				startActivity(intent1);
			} else {
				Intent intent23 = new Intent();
				intent23.setClass(TwoHandMainActivity.this, LoginActivity.class);
				startActivity(intent23);
			}
			break;
		case R.id.btn_system:
			Intent intent1 = new Intent();
			intent1.setClass(TwoHandMainActivity.this, AboutUs.class);
			startActivity(intent1);
			break;

		case R.id.one_row_two:
			Intent intent12 = new Intent();
			intent12.setClass(TwoHandMainActivity.this, BikeActivity.class);
			Bundle bundle12 = new Bundle();
			bundle12.putString("cid", "2");
			intent12.putExtras(bundle12);
			startActivity(intent12);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;

		case R.id.two_row_one:
			Intent intent21 = new Intent();
			intent21.setClass(TwoHandMainActivity.this, BikeActivity.class);
			Bundle bundle21 = new Bundle();
			bundle21.putString("cid", "3");
			intent21.putExtras(bundle21);
			startActivity(intent21);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.two_row_two:
			Intent intent22 = new Intent();
			intent22.setClass(TwoHandMainActivity.this, BikeActivity.class);
			Bundle bundle22 = new Bundle();
			bundle22.putString("cid", "4");
			intent22.putExtras(bundle22);
			startActivity(intent22);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.three_row_one:
			Intent intent31 = new Intent();
			intent31.setClass(TwoHandMainActivity.this, BikeActivity.class);
			Bundle bundle31 = new Bundle();
			bundle31.putString("cid", "5");
			intent31.putExtras(bundle31);
			startActivity(intent31);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		case R.id.three_row_two:
			Intent intent32 = new Intent();
			intent32.setClass(TwoHandMainActivity.this, GoodManActivity.class);
			startActivity(intent32);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			break;
		}

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			stopService(new Intent(this, Appsevice.class));
			// �����˳��Ի���
			AlertDialog isExit = new AlertDialog.Builder(this).create();
			// ���öԻ������
			isExit.setTitle("ϵͳ��ʾ");
			// ���öԻ�����Ϣ
			isExit.setMessage("ȷ��Ҫ�˳���");
			// ���ѡ��ť��ע�����
			isExit.setButton("ȷ��", listener);
			isExit.setButton2("ȡ��", listener);
			// ��ʾ�Ի���
			isExit.show();
		}
		return super.onKeyDown(keyCode, event);
	}

	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����
				if (Config.User.getFlag()) {
					Config.User.setFlag(false);
				}
				close();
				finish();
				break;
			case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���
				break;
			default:
				break;
			}
		}
	};

	// ���͹㲥֪ͨ���д���ر�
	public void close() {
		Intent intent = new Intent();
		intent.setAction("ExitApp");
		this.sendBroadcast(intent);
		super.finish();
	}

	// ��������ҳviewpager
	// �л���ǰ��ʾ��ͼƬ
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// �л���ǰ��ʾ��ͼƬ
		};
	};

	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// ��Activity��ʾ������ÿ3�����л�һ��ͼƬ��ʾ
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 0, 5,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	protected void onStop() {
		// ��Activity���ɼ���ʱ��ֹͣ�л�
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	private class ScrollTask implements Runnable {
		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % views.size();
				handler.obtainMessage().sendToTarget(); // ͨ��Handler�л�ͼƬ
			}
		}

	}

	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
			arg0.removeView((View) arg2);
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public Object instantiateItem(ViewGroup arg0, int arg1) {
			arg0.addView(mListViews.get(arg1), 0);
			mListViews.get(arg1).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {// ���ͼƬ�������¼�
					startActivity(new Intent(TwoHandMainActivity.this,
							IntroduceAppFunctionActivity.class));
					overridePendingTransition(android.R.anim.slide_in_left,
							android.R.anim.slide_out_right);
				}
			});
			return mListViews.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

}
