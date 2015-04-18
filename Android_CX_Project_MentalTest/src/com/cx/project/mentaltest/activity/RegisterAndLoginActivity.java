package com.cx.project.mentaltest.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.custom.HeadView;
import com.cx.project.mentaltest.entity.User;
import com.cx.project.mentaltest.utils.DataManagerUtil;

public class RegisterAndLoginActivity extends PortraitActivity implements
		OnClickListener {
	
	//控件相关
	private HeadView headView;
	private Button btnRegister;  //注册按钮
	private Button btnLogin;  //登录按钮
	private Button btnCancel; //注销
	private EditText etEmail;  //邮箱
	private EditText etPassword; //密码
	private EditText etPassword1; //确认密码
	private Button btnSure;
	private LinearLayout llPassword1;
	
	//数据库相关
	private DataManagerUtil dataManagerUtil;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataManagerUtil = new DataManagerUtil(this);
		setContentView(R.layout.activity_register_and_login);
		initView();
	}

	private void initView() {
		headView = (HeadView) findViewById(R.id.head_view);
		btnRegister = (Button)  findViewById(R.id.btn_register);
		btnLogin = (Button)  findViewById(R.id.btn_login);
		btnCancel = (Button)  findViewById(R.id.btn_cancel);
		etEmail = (EditText)  findViewById(R.id.et_email);
		etPassword = (EditText)  findViewById(R.id.et_password);
		etPassword1 = (EditText)  findViewById(R.id.et_password1);
		llPassword1 = (LinearLayout) findViewById(R.id.ll_password1);
		btnSure = (Button) findViewById(R.id.btn_sure);
		
		headView.setLeftOnClickListener(this);
		btnRegister.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnSure.setOnClickListener(this);
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(User.isLoginState()){
			etEmail.setText(User.getInstance().getEmail());
			etPassword.setText(User.getInstance().getPassword());
			btnRegister.setVisibility(View.GONE);
			btnLogin.setVisibility(View.GONE);
			btnCancel.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case HeadView.LEFT_ICON:
			finish();
			break;
		case R.id.btn_cancel:
			clickCancel();
			break;
		case R.id.btn_register:
			clickRegister();
			break;
		case R.id.btn_login:
			clickLogin();
			break;
		case R.id.btn_sure:
			clickRegisterSure();
			break;

		default:
			break;
		}

	}

	private void clickRegisterSure() {
		String email = etEmail.getText().toString();
		String password = etPassword.getText().toString();
		String password1 = etPassword1.getText().toString();
		
		if("".equals(email)){
			Toast.makeText(this, "邮箱不能为空！",Toast.LENGTH_SHORT ).show();
		}else if("".equals(password)){
			Toast.makeText(this, "密码不能为空！",Toast.LENGTH_SHORT ).show();
		}else if("".equals(password1)){
			Toast.makeText(this, "确认密码不能为空！",Toast.LENGTH_SHORT ).show();
		}else if(!password.equals(password1)){
			Toast.makeText(this, "两个密码不一样！",Toast.LENGTH_SHORT ).show();
		}else{
			User user = new User(email, password);
			if(user.checkEmail(dataManagerUtil.openDatabase())){
				Toast.makeText(this, "该邮箱已被注册", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
				user.inserSelf(dataManagerUtil.openDatabase());
				
				btnLogin.setVisibility(View.VISIBLE);
				btnRegister.setVisibility(View.VISIBLE);
				llPassword1.setVisibility(View.GONE);
				btnCancel.setVisibility(View.GONE);
				btnSure.setVisibility(View.GONE);
			}
		}
	}

	private void clickCancel() {
		btnLogin.setVisibility(View.VISIBLE);
		btnRegister.setVisibility(View.VISIBLE);
		btnCancel.setVisibility(View.GONE);
		btnSure.setVisibility(View.GONE);
		
		User.getInstance().setLogin(false);
	}

	private void clickRegister() {
		llPassword1.setVisibility(View.VISIBLE);
		btnRegister.setVisibility(View.GONE);
		btnLogin.setVisibility(View.GONE);
		btnSure.setVisibility(View.VISIBLE);
		btnCancel.setVisibility(View.GONE);
		headView.setTitle("注册");
	}
	
	

	private void clickLogin() {
		String email = etEmail.getText().toString();
		String password = etPassword.getText().toString();
		if("".equals(email)){
			Toast.makeText(this, "邮箱不能为空！",Toast.LENGTH_SHORT ).show();
		}else if("".equals(password)){
			Toast.makeText(this, "密码不能为空！",Toast.LENGTH_SHORT ).show();
		}else{
			User user = new User(email, password);
			if(!user.checkEmail(dataManagerUtil.openDatabase()) ){
				Toast.makeText(this, "账号不存在,请先注册",Toast.LENGTH_SHORT ).show();
			}else{
				boolean isLogin=user.checkLogin(dataManagerUtil.openDatabase());
				if(isLogin){
					User sUser=User.getInstance();
					sUser.setEmail(email);
					sUser.setPassword(password);
					sUser.setLogin(true);
					btnLogin.setVisibility(View.GONE);
					btnRegister.setVisibility(View.GONE);
					btnCancel.setVisibility(View.VISIBLE);
					
					finish();
				}else{
					Toast.makeText(this, "密码错误！",Toast.LENGTH_SHORT ).show();
				}
			}
			
		}
	}

}
