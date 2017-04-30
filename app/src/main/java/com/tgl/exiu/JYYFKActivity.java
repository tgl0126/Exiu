package com.tgl.exiu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tgl.beans.JYYFK;
import com.tgl.beans.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class JYYFKActivity extends AppCompatActivity {
@BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.toolbar_back)
    Button btn_back;
    @BindView(R.id.et_jyyfk)
    EditText et_jyyfk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jyyfk);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //toolbar返回按钮
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //发送建议或反馈
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_jyyfk.getText().equals("")){
                JYYFK jianyi=new JYYFK();
                jianyi.setUser(BmobUser.getCurrentUser(UserBean.class));
                jianyi.setTextCentent(et_jyyfk.getText().toString());
                jianyi.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (null==e){
                            Toast.makeText(JYYFKActivity.this, "反馈成功!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            else {
                    Toast.makeText(JYYFKActivity.this, "未输入任何信息!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
