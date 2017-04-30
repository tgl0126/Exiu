package com.tgl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tgl.beans.UserBean;
import com.tgl.exiu.BecomeExiuActivity;
import com.tgl.exiu.JYYFKActivity;
import com.tgl.exiu.LoginActivity;
import com.tgl.exiu.R;
import com.tgl.exiu.SetActivity;
import com.tgl.exiu.UserInfoActivity;
import com.tgl.utils.SharedPreferenceUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;


public class MyInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listView;
    private View view;
    private String mParam1;
    private String mParam2;


    @BindView(R.id.btn_user_quitLogin)//退出登录按钮
            Button btn_user_quitLogin;
    @BindView(R.id.tv_user_name)//用户名
            TextView tv_user_name;
    @BindView(R.id.tv_user_qianming)//个性签名
            TextView tv_user_qianming;
    @BindView(R.id.imgv_user_head)//头像
            ImageView imgv_user_head;
    @BindView(R.id.btn_user_go)//button图标箭头,前进
            Button btn_user_go;
    @BindView(R.id.item_user_info)
    RelativeLayout item_user_info;
    @BindView(R.id.lineView_set)
    LinearLayout liner_set;
    @BindView(R.id.lineView_published)
    LinearLayout liner_published;
    @BindView(R.id.lineView_jianyi)
    LinearLayout liner_jianyi;
    @BindView(R.id.lineView_becomeexiu)
    LinearLayout liner_becomeexiu;

    private UserBean user;

    public MyInfoFragment() {
    }

    public static MyInfoFragment newInstance(String param1, String param2) {
        MyInfoFragment fragment = new MyInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * adapter 实现对listview 的初始化
         * */
        view = inflater.inflate(R.layout.fragment_myinfo, container, false);
        ButterKnife.bind(this, view);
        File file = new File("/data/data/com.tgl.exiu/shared_prefs/userInfo.xml");
        if (file.exists()) {
            if (SharedPreferenceUtil.getBooleanInfo(getActivity(), "isLogin")) {
                init();
            } else {
                Toast.makeText(getContext(), "您还未登录!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getContext(), "用户数据不存在", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void init() {
        //获取用户名,个性签名,头像等 信息      本地获取,因为使用bmob自动创建了一个Shaerprefenrces
//        if (SharedPreferenceUtil.getBooleanInfo(getActivity(), "isLogin")) {
        user = BmobUser.getCurrentUser(UserBean.class);
        String userName = user.getUsername();
        String qianming = user.getPersonalSign();
        tv_user_name.setText(userName);
        tv_user_qianming.setText(qianming);
//        }

        //退出登录按钮点击事件
        btn_user_quitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注销登录
                BmobUser.logOut();
                //设置登录状态为false
                SharedPreferenceUtil.setUserInfo(getContext(), "isLogin", false);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "退出", Toast.LENGTH_SHORT).show();
            }
        });
        //点击修改自己信息的点击事件
        item_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
            }
        });
        //点击我发布的
        liner_published.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "我发布的", Toast.LENGTH_SHORT).show();
            }
        });
        //点击设置
        liner_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent intent=new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
            }
        });
        //点击成为维修人员
        liner_becomeexiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BecomeExiuActivity.class);
                startActivity(intent);
            }
        });
        //点击建议与反馈
        liner_jianyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), JYYFKActivity.class);
                startActivity(intent);
            }
        });
    }
}
