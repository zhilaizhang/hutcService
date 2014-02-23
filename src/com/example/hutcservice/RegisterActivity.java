package com.example.hutcservice;

import java.util.concurrent.ExecutionException;

import com.hutcservice.common.*;
import com.hutcservice.database.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity{
	  
	  private  EditText name;
	  private  EditText pass;
	  private  EditText rpass;
	  private Button register;
	protected void onCreate(Bundle savedInstanceState) {
        	   super.onCreate(savedInstanceState);
       		   setContentView(R.layout.register);
       		   
       		name=(EditText)findViewById(R.id.username);
       		pass=(EditText)findViewById(R.id.password);
       		rpass=(EditText)findViewById(R.id.confirmpassword);
       		register=(Button)findViewById(R.id.btn_register);
       		register.setOnClickListener(this);
           }
	 public void onClick(View v){
		 switch(v.getId()){
		 case R.id.btn_back:
				finish();
				break;
		 case R.id.btn_register:
			 register();
			 break;
		 }
	 }
	 public void register(){
		 //Toast.makeText(this, "test!", Toast.LENGTH_LONG).show();
		//UserCommand uc=new UserCommand(this);
		RegisterAsyncCallWS rac=new RegisterAsyncCallWS();
		 if(name.getText().toString().equals("")||pass.getText().toString().equals("")||
				 rpass.getText().toString().equals("")){
			 Toast.makeText(this, "������û��������벻��Ϊ��!", Toast.LENGTH_LONG).show();
		 }else{
			 if(!(pass.getText().toString().trim()).equals(rpass.getText().toString().trim())){
				 Toast.makeText(this, "�����������벻ͬ����ȷ��!", Toast.LENGTH_LONG).show();
			 }else{				
				try {
					if(rac.execute(name.getText().toString(),pass.getText().toString()).get()){
						Toast.makeText(this, "ע��ɹ�!", Toast.LENGTH_LONG).show();
						Intent intent1=new Intent();
						intent1.setClass(RegisterActivity.this, LoginActivity.class);
						startActivity(intent1);
					}else{
						Toast.makeText(this, "ע��ʧ��!", Toast.LENGTH_LONG).show();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// uc.add(name.getText().toString(), pass.getText().toString()); //���浽���ݿ���
		 }
	 }
	 }
	}
