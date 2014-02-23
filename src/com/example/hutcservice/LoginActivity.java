package com.example.hutcservice;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.hutcservice.common.JudgeLinkInternet;
import com.hutcservice.common.LoginAsyncCallWS;
import com.hutcservice.common.Config;
import com.hutcservice.database.*;

public class LoginActivity extends BaseActivity {
	private EditText name;
	private EditText pass;
	private Button login;
	private CheckBox cbpass;
	private CheckBox cblogin;
	public JudgeLinkInternet judgeInter = new JudgeLinkInternet();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		name = (EditText) findViewById(R.id.username);
		pass = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.btn_login);
		login.setOnClickListener(this);
		cbpass = (CheckBox) findViewById(R.id.cb_rememberPass);
		cblogin = (CheckBox) findViewById(R.id.cb_autoLogin);
		if (Config.User.getName() != null) {
			name.setText(Config.User.getName());
		}
		// ����û�ѡ���˼�ס���룬����ص�ʱ��Ͱ��û�����������ʾ���û�
		if (Config.User.getRememberpass()) {
			cbpass.setChecked(true);
			pass.setText(Config.User.getPass());
		}
		Config.User.setAutologin(false); // ��ʱ��Ϊfalse
		if (Config.User.getAutologin()) {
			cblogin.setChecked(true);
			login();
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_register:
			Intent it = new Intent(this, RegisterActivity.class);
			startActivity(it);
			break;
		case R.id.btn_login:
			login();
			break;
		}
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			finish();
		}
	};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction("ExitApp");
		this.registerReceiver(broadcastReceiver, filter);
	}

	private void login() {
		if (!judgeInter.IsHaveInternet(this)) {
			Toast.makeText(this, "��ǰ���粻����!", Toast.LENGTH_LONG).show();
			return;
		}
		UserCommand uc = new UserCommand(this);
		if ((name.getText().toString()).equals("")
				|| (pass.getText().toString()).equals("")) {
			Toast.makeText(this, "������û���������Ϊ��!", Toast.LENGTH_LONG).show();
		} else {
			if (cbpass.isChecked()) {
				Config.User.setRememberpass(true);
			} else {
				Config.User.setRememberpass(false);
			}
			if (cblogin.isChecked()) {
				Config.User.setAutologin(true);
			} else {
				Config.User.setAutologin(false);
			}
			LoginAsyncCallWS ac = new LoginAsyncCallWS(); // �����첽��ʵ�ֵ�¼
			try {
				if (ac.execute(name.getText().toString(),
						pass.getText().toString()).get()) {
					Config.User.setName(name.getText().toString()); // ��¼�ɹ����û������ݱ��浽sharedPreference��
					Config.User.setPass(pass.getText().toString());
					Config.User.setFlag(true); // ��¼�ɹ���flag��Ϊtrue���൱��cookie
					Intent intent = new Intent(this, TwoHandMainActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(this, "������û������������!", Toast.LENGTH_LONG)
							.show();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
