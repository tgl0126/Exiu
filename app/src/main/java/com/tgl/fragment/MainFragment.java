package com.tgl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.tgl.beans.UserBean;
import com.tgl.exiu.LoginActivity;
import com.tgl.exiu.R;
import com.tgl.utils.SharedPreferenceUtil;
import com.tgl.utils.TimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View view;

    private static final int REFRESH_COMPLETE = 1111;
    private ViewPager viewPager;//图片轮播的viewpage
    private SimpleAdapter contentAdapter;//暂时用的测试内容list适配器
    private List<Fragment> list_content;//发布的内容list
    private List<Map<String, Object>> list;
    //    private List<Image> list_img;//图片轮播的list

    // Fragment管理对象
    private FragmentManager fm;
    private FragmentTransaction beginTransaction;
    private MyInfoFragment mMyInfoFragment;
    private MainFragment mMainFragment;
    private Fragment fragment;//定义一个Fragment，来做为转换的
    private UserBean user;
    //首页图标人物
//    @BindView(R.id.main_img_myinfo)
//    ImageView main_img_myinfo;
    private ImageView main_img_myinfo;
    //listView
    @BindView(R.id.main_listView)
    ListView mListView;
    //底部导航栏
    @BindView(R.id.main_swipe_refresh_widget)
    SwipeRefreshLayout mSwipeLayout;

    //Handle更新UI
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    for (int i = 0; i < 1; i++) {
                        Map<String, Object> map02 = new HashMap<String, Object>();
                        map02.put("head", R.drawable.test_hd);
                        map02.put("userName", "测试" + i);
                        /**
                         * Create by 山花烂漫
                         * on 2017/3/29
                         *到时候替换为创建时间
                         **/
                        Calendar calendar = GregorianCalendar.getInstance();
                        calendar.set(Calendar.YEAR, 2017);
                        calendar.set(Calendar.MONTH, Calendar.JANUARY);
                        calendar.set(Calendar.DATE, 1);
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        Date day = calendar.getTime();

                        map02.put("date", TimeFormat.getTimeFormat(day));//DateFormat.format("yy-MM-dd HH:mm",new Date())
                        map02.put("text_content", "测试文本测试文本测试文本测试文本测试文本测试文本" + i);

                        map02.put("whereFrom", " 西昌学院" + i);
                        list.add(i, map02);
                    }
                    contentAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //构造器
    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        //构造页面view
        view = inflater.inflate(R.layout.fragment_main, container, false);
        main_img_myinfo = (ImageView) view.findViewById(R.id.main_img_myinfo);
        //绑定Fragment
        ButterKnife.bind(this, view);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //开始刷新
            // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 3000);
                Toast.makeText(getActivity(), "正在刷新", Toast.LENGTH_SHORT).show();
            }
        });
        //设置更新时的颜色，目前感觉没有用处，未显示出来
        mSwipeLayout.setColorSchemeColors(R.color.blue,
                R.color.red,
                R.color.blue,
                R.color.red);
        //设置适配器

        contentAdapter = new SimpleAdapter(getActivity(), getDada(), R.layout.main_listview_content, new String[]{"head", "userName", "date", "text_content", "whereFrom"},
                new int[]{R.id.main_img_userHead, R.id.main_tv_userName, R.id.main_tv_publishDate, R.id.main_tv_content, R.id.main_tv_publishcontent_wherefrom});
        mListView.setAdapter(contentAdapter);
//        initContent();
        clickImgIfNotLogin();
        return view;
    }


    private void clickImgIfNotLogin() {

        main_img_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SharedPreferenceUtil.getBooleanInfo(getContext(),"isLogin")) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
               /*     fm = getActivity().getSupportFragmentManager();
                     beginTransaction = fm.beginTransaction();
                    if (mMyInfoFragment == null) {
                        mMyInfoFragment = MyInfoFragment.newInstance("1", "1");
                    }
                    beginTransaction.replace(R.id.mMainFrame,mMyInfoFragment);
                    beginTransaction.commit();
                 */
                }
            }
        });
    }
    /*private void initContent() {
        mListView = (ListView) view.findViewById(R.id.main_listView);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.main_swipe_refresh_widget);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //开始刷新
            // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 3000);
                Toast.makeText(getActivity(), "正在刷新", Toast.LENGTH_SHORT).show();
            }
        });
        //设置更新时的颜色，目前感觉没有用处，未显示出来
        mSwipeLayout.setColorSchemeColors(R.color.blue,
                R.color.red,
                R.color.blue,
                R.color.red);
        //设置适配器

        SimpleAdapter contentAdapter = new SimpleAdapter(getActivity(), getDada(), R.layout.main_listview_content, new String[]{"head", "userName", "date", "text_content", "img", "whereFrom","goodNum"},
                new int[]{R.id.main_img_userHead, R.id.main_tv_userName, R.id.main_tv_publishDate, R.id.main_tv_content, R.id.main_img_content_img1, R.id.main_tv_publishcontent_wherefrom,R.id.main_img_publishcontent_goodNum});
        mListView.setAdapter(contentAdapter);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public List<Map<String, Object>> getDada() {
        // "head", "userName", "date","text_ontent","img","wherefrom","good","isgo"
        list = new ArrayList<Map<String, Object>>();
//        this.list = list;
        Map<String, Object> map01 = new HashMap<String, Object>();
        map01.put("head", R.drawable.cry);
        map01.put("userName", "测试");
        map01.put("date", "三天前");
        map01.put("text_content", "测试文本测试文本测试文本测试文本测试文本测试文本");
        map01.put("img", R.drawable.log);
        map01.put("whereFrom", "西昌学院");

        Map<String, Object> map02 = new HashMap<String, Object>();
        map02.put("head", R.drawable.test_hd);
        map02.put("userName", "测试姓名");
        map02.put("date", "测试时间");
        map02.put("text_content", "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本");

        map02.put("whereFrom", "西昌学院");
        list.add(map01);
//        list.add(map02);
        return list;
    }

    /**
     * 用作对展示发布内容的适配器
     */
    private class myAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private View mview;
        private int layoutId;

        @Override
        public int getCount() {
            return list_content.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                mview = inflater.inflate(layoutId, viewGroup, false);
            } else {
                mview = view;
            }
            return mview;
        }
    }

}
