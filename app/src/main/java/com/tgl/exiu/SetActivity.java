package com.tgl.exiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_back)
    Button toolbar_back;
    @BindView(R.id.line_shiyong)
    LinearLayout line_shiyong;
    @BindView(R.id.line_banben)
    LinearLayout line_banben;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //返回
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        line_shiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,How2UseActivity.class);
                startActivity(intent);
            }
        });
        line_banben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,Set_banben_Activity.class);
                startActivity(intent);
            }
        });
    }

}
