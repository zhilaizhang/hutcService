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

	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<View> views;// Tab页面列表
	private int currentItem = 0; // 当前图片的索引号
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
		// 取得窗口属性
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

		// 弹出窗口开始
		LayoutInflater inflater1 = LayoutInflater.from(this);
		// 引入窗口配置文件
		View view = inflater1.inflate(R.layout.popupwindow, null);
		// 创建PopupWindow对象
		popup = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);
		// 需要设置一下此参数，点击外边可消失
		popup.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		popup.setOutsideTouchable(true);
		// 设置此参数获得焦点，否则无法点击
		popup.setFocusable(true);

		// 点击按钮more出现新的层
		more.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (popup.isShowing()) {
					// 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
					popup.dismiss();
				} else {
					// 显示窗口
					popup.showAsDropDown(v);
				}
			}
		});
		// 弹出窗口结束

		// 首页动画
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
		text_title.setText("师院信息服务开测啦！www.kuoyisa.cn");
		ImageView img2 = (ImageView) view2.findViewById(R.id.viewpager_image);
		ImageView img3 = (ImageView) view3.findViewById(R.id.viewpager_image);
		ImageView img4 = (ImageView) view4.findViewById(R.id.viewpager_image);
		img2.setImageResource(R.drawable.viewpage);
		img3.setImageResource(R.drawable.goodthing);
		img4.setImageResource(R.drawable.fabu);

		viewPager.setAdapter(new MyViewPagerAdapter(views));// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
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
		case R.id.popup_ershoufabu: // 二手发布
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
		case R.id.popup_ershouqiugou: // 二手求购
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
		case R.id.popup_fulitongxue: // 福利同学
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
		case R.id.popup_shijinbumei: // 拾金不昧
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
		case R.id.popup_xunwuqishi: // 寻物启事
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
			// 创建退出对话框
			AlertDialog isExit = new AlertDialog.Builder(this).create();
			// 设置对话框标题
			isExit.setTitle("系统提示");
			// 设置对话框消息
			isExit.setMessage("确定要退出吗");
			// 添加选择按钮并注册监听
			isExit.setButton("确定", listener);
			isExit.setButton2("取消", listener);
			// 显示对话框
			isExit.show();
		}
		return super.onKeyDown(keyCode, event);
	}

	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
				if (Config.User.getFlag()) {
					Config.User.setFlag(false);
				}
				close();
				finish();
				break;
			case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
				break;
			default:
				break;
			}
		}
	};

	// 发送广播通知所有窗体关闭
	public void close() {
		Intent intent = new Intent();
		intent.setAction("ExitApp");
		this.sendBroadcast(intent);
		super.finish();
	}

	// 以下是首页viewpager
	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每3秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 0, 5,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	private class ScrollTask implements Runnable {
		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % views.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
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
				public void onClick(View v) {// 点击图片产生的事件
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
