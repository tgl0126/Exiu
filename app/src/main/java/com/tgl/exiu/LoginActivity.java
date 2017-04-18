package com.tgl.exiu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tgl.beans.UserBean;
import com.tgl.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

import static com.tgl.exiu.R.id.et_UserName;
import static com.tgl.exiu.R.id.et_password;

public class LoginActivity extends Activity {
    @BindView(et_UserName)
    EditText ex_login_UserName;
    @BindView(et_password)
    EditText ex_login_PWD;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.clicktoregister)
    TextView clickToRegister;
    private int request_code = 0;
    private UserBean user;
    private Handler mHandler;
//    private SharedPreferenceUtil sp_userinfo;
//    UserBean user = new UserBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        /**
         * 点击注册事件
         * */
        clickToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, request_code);
            }
        });

        /**
         * 点击登录事件
         * */
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(ex_login_UserName.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "未输入用户名！", Toast.LENGTH_SHORT).show();
                } else if ("".equals(ex_login_PWD.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "未输入密码！", Toast.LENGTH_SHORT).show();
                } else {
                    //登录操作
                    mHandler=new Handler();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            user = new UserBean();
                            BmobUser.loginByAccount(ex_login_UserName.getText().toString(),
                                    ex_login_PWD.getText().toString(),
                                    new LogInListener<UserBean>() {
                                        @Override
                                        public void done(UserBean userBean, BmobException e) {
                                            if (userBean != null) {
                                                Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                                                //跳转到主页面
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                                //保存登录状态
                                                SharedPreferenceUtil.setUserInfo(LoginActivity.this,"isLogin", true);
                                            } else {
                                                Toast.makeText(LoginActivity.this, "登录失败!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    });

                }
            }
        });
    }
    //Register返回的帐号和密码

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                String userName = data.getStringExtra("userName");
                String passWord = data.getStringExtra("passWord");
                ex_login_UserName.setText(userName);
                ex_login_PWD.setText(passWord);
                break;
        }
    }
}
