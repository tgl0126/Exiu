package com.tgl.exiu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    private EditText ex_login_UserName;
    private EditText ex_login_PWD;
    private TextView tv_register;
    private Button btn_login;
    private TextView clickToRegister;
    private int request_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ex_login_UserName=(EditText) findViewById(R.id.et_UserName);
        ex_login_PWD=(EditText)findViewById(R.id.et_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        clickToRegister = (TextView) findViewById(R.id.clicktoregister);
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
                request_code=0;
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
//                startActivityForResult(intent,request_code);
//                startActivity(intent);
            }
        });

        /**
         * 点击登录事件
         * */

    }
}
