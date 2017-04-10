package com.tgl.exiu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tgl.beans.UserBean;
import com.tgl.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends Activity {
    private UserBean user;//用户对象
    private final int RESULT_CODE = 0;
    private SharedPreferenceUtil sharedPreference;
    @BindView(R.id.et_UserName)//帐号
            EditText et_UserName;
    @BindView(R.id.et_password)//密码
            EditText et_password;
    @BindView(R.id.et_tel)//手机号
            EditText et_tel;
    @BindView(R.id.btn_hqyzm)//按钮获得验证码
            Button btn_hqyzm;
    @BindView(R.id.btn_register)//点击注册
            Button btn_register;
    @BindView(R.id.et_yzm)//验证码
    EditText et_yzm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private boolean isSendedCode = false;//判断是否已经发送过短信,发送了就不在发送短信了.

    private void init() {
        btn_register.setEnabled(true);//暂时取消获得验证码这个效果,便于测试,测试完后删除
        user = new UserBean();
        //给Button设置点击时间,触发倒计时
        final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);
        //获取验证码按钮
        btn_hqyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得验证码
                if (!"".equals(et_tel.getText().toString())) {
                    myCountDownTimer.start();//开始倒计时
                    if (!isSendedCode) {
                        BmobSMS.requestSMSCode(getBaseContext(), et_tel.getText().toString(), "exiu", new RequestSMSCodeListener() {
                            @Override
                            public void done(Integer integer, cn.bmob.sms.exception.BmobException e) {
                                if (null == e) {//发送短信成功!
//                                    Toast.makeText(RegisterActivity.this, "验证码正在努力赶来!", Toast.LENGTH_SHORT).show();
                                    //将注册按钮设置为可点击
                                    btn_register.setEnabled(true);
                                    isSendedCode = true;
                                } else {
                                    Toast.makeText(RegisterActivity.this, "对不起,验证码迷路了!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "验证码已经发送过啦,请注意查收!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "老铁,手机号不能为空!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(et_UserName.getText().toString().trim());//设置用户名,继承自BmobUser
                user.setPassword(et_password.getText().toString().trim());//设置密码,继承自BmobUser
                user.setMobilePhoneNumber(et_tel.getText().toString());//设置电话
//                user.setHead();//设置头像
                user.setAddress("NULL");//设置默认地址
                user.setExiuStaff(false);//设置是否是员工
                user.setStars(0);//设置星级
                user.setSex(true);//设置性别,男为true,女为false
                user.setPersonalSign("为何人总是在失去的时候才懂得珍惜!");
                //验证验证码是否正确,正确进入页面
               /* user.signOrLoginByMobilePhone(et_tel.getText().toString(), et_yzm.getText().toString(), new LogInListener<UserBean>() {

                    @Override
                    public void done(UserBean user, BmobException e) {
                        if(user!=null){
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "对不起,注册失败了!"+e.getMessage()+e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
                user.signUp(new SaveListener<UserBean>() {
                    @Override
                    public void done(UserBean userBean, BmobException e) {
                        if (null == e) {
                            Intent intent = new Intent();
                            intent.putExtra("userName", et_UserName.getText().toString());
                            intent.putExtra("passWord", et_password.getText().toString());
                            RegisterActivity.this.setResult(RESULT_CODE, intent);
                            Toast.makeText(RegisterActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "对不起,注册失败了!"+e.getErrorCode()+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //注册或登录
             /*   user.signOrLogin(et_yzm.getText().toString(), new SaveListener<UserBean>() {
                    @Override
                    public void done(UserBean userBean, BmobException e) {
                        if (null == e) {
                            Intent intent = new Intent();
                            intent.putExtra("userName", et_UserName.getText().toString());
                            intent.putExtra("passWord", et_password.getText().toString());
                            RegisterActivity.this.setResult(RESULT_CODE, intent);
                            Toast.makeText(RegisterActivity.this, "注册成功!正在跳转登录界面!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "对不起,注册失败了!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
            }
        });
    }

    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            btn_hqyzm.setClickable(false);
            btn_hqyzm.setText(l / 1000 + "s");
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            btn_hqyzm.setText("重新获取验证码");
            //设置可点击
            btn_hqyzm.setClickable(true);
        }
    }
}

