package com.tgl.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tgl.beans.UserBean;
import com.tgl.exiu.LoginActivity;
import com.tgl.exiu.R;
import com.tgl.exiu.UserInfoActivity;
import com.tgl.utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private OnFragmentInteractionListener mListener;
    private UserBean user;
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
        //对菜单选项的填充
        listView = (ListView) view.findViewById(R.id.listview_myinfo);
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), getDada(),
                R.layout.layout_adapter_myinfo_listview, new String[]{"img_icon", "context", "icon_go"},
                new int[]{R.id.img_myinfo_item_icon, R.id.tv_myinfo_item_content, R.id.btn_myinfo_item_icon_go});
        listView.setAdapter(adapter);
        if (SharedPreferenceUtil.getBooleanInfo(getActivity(), "isLogin")) {
            init();
        }else {
            Toast.makeText(getContext(), "您还未登录!", Toast.LENGTH_SHORT).show();
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
                SharedPreferenceUtil.setUserInfo(getContext(), "isLogin", false);//注销登录
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


    }


    /**
     * 对listview进行adapter填充
     */

    public List<Map<String, Object>> getDada() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map01 = new HashMap<String, Object>();
        map01.put("img_icon", R.drawable.home);
        map01.put("context", "我发布过的");
        map01.put("icon_go", R.drawable.go);
        list.add(map01);

        Map<String, Object> map02 = new HashMap<String, Object>();
        map02.put("img_icon", R.drawable.mesage);
        map02.put("context", "我维修过的");
        map02.put("icon_go", R.drawable.go);
        list.add(map02);


        Map<String, Object> map03 = new HashMap<String, Object>();
        map03.put("img_icon", R.drawable.mesage);
        map03.put("context", "我赞过的");
        map03.put("icon_go", R.drawable.go);
        list.add(map03);

        Map<String, Object> map04 = new HashMap<String, Object>();
        map04.put("img_icon", R.drawable.mesage);
        map04.put("context", "建议与反馈");
        map04.put("icon_go", R.drawable.go);
        list.add(map04);

        Map<String, Object> map05 = new HashMap<String, Object>();
        map05.put("img_icon", R.drawable.mesage);
        map05.put("context", "设置");
        map05.put("icon_go", R.drawable.go);
        list.add(map05);

        Map<String, Object> map06 = new HashMap<String, Object>();
        map06.put("img_icon", R.drawable.mesage);
        map06.put("context", "我要成为维修人员");
        map06.put("icon_go", R.drawable.go);
        list.add(map06);
        return list;
    }

    /*  public void onButtonPressed(Uri uri) {
          if (mListener != null) {
              mListener.onFragmentInteraction(uri);
          }
      }

      @Override
      public void onAttach(Context context) {
          super.onAttach(context);
          if (context instanceof OnFragmentInteractionListener) {
              mListener = (OnFragmentInteractionListener) context;
          } else {
              throw new RuntimeException(context.toString()
                      + " must implement OnFragmentInteractionListener");
          }
      }

      @Override
      public void onDetach() {
          super.onDetach();
          mListener = null;
      }
  */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
