package com.tgl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.tgl.adapter.myListAdapter;
import com.tgl.beans.PublishList;
import com.tgl.exiu.LoginActivity;
import com.tgl.exiu.PublishDetailsActivity;
import com.tgl.exiu.R;
import com.tgl.utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View view;

    private static final int REFRESH_COMPLETE = 1111;
    //    private ViewPager viewPager;//图片轮播的viewpage
    private myListAdapter contentAdapter;//暂时用的测试内容list适配器
    private List<Map<String, Object>> list;//发布内容的list,
    //    private List<Image> list_img;//图片轮播的list
    private List<PublishList> plist = new ArrayList<PublishList>();
    // Fragment管理对象
    private FragmentManager fm;
    private FragmentTransaction beginTransaction;
    private MyInfoFragment mMyInfoFragment;
    private MainFragment mMainFragment;
    private Fragment fragment;//定义一个Fragment，来做为转换的
    //    private UserBean user;//实体用户信息
//    private PublishBean publish;//实体发布内容
    //首页图标人物
    @BindView(R.id.main_img_myinfo)
    ImageView main_img_myinfo;
    //    private ImageView main_img_myinfo;
    //listView,发布的内容显示在这里面
    @BindView(R.id.main_listView)
    ListView mListView;
    //底部导航栏
    @BindView(R.id.main_swipe_refresh_widget)
    SwipeRefreshLayout mSwipeLayout;

    //    private List<PublishBean> pb;
    private int size = 0;
    //Handle更新UI
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    contentAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    mSwipeLayout.setRefreshing(false);
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
//        main_img_myinfo = (ImageView) view.findViewById(R.id.main_img_myinfo);
        //绑定Fragment
        ButterKnife.bind(this, view);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //开始刷新
            // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
            public void onRefresh() {
                init();
              /*  mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        BmobQuery query = new BmobQuery("PublishBean");
                        //查询所有pubish,每次
                        query.setLimit(size+5);
                        query.setSkip(size);
                        //按时间排序
                        query.order("-createdAt");
                        //查询数据表 PublishBean中所有数据,获取id的user
                        query.findObjects(new FindListener<PublishBean>() {
                            @Override
                            public void done(List<PublishBean> list, BmobException e) {
                                if (e == null) {
                                    if (size < list.size()) {
                                        for (int i = 0; i < list.size(); i++) {
                                            final PublishBean publish = list.get(i);
                                            UserBean user = publish.getUserBean();
                                            Toast.makeText(getActivity(), "listsize" + list.size()+"size"+size, Toast.LENGTH_SHORT).show();
                                            //查询publish对应user的id,获取对应的user对象
                                            BmobQuery<UserBean> query_user = new BmobQuery<>();
                                            query_user.getObject(user.getObjectId(), new QueryListener<UserBean>() {
                                                @Override
                                                public void done(UserBean userBean, BmobException e) {
                                                    if (e == null) {
                                                        UserBean user = userBean;
                                                        PublishList p1 = new PublishList(publish, user);
                                                        plist.add(p1);
                                                        size++;
                                                        Toast.makeText(getActivity(),"size"+size, Toast.LENGTH_SHORT).show();
                                                        mHandler.sendEmptyMessage(0);
                                                    } else {
                                                        Toast.makeText(getContext(), "未找到该用户数据", Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            });
                                        }
                                    } else {
                                        Toast.makeText(getContext(), "没有更多的数据啦!", Toast.LENGTH_SHORT).show();
                                        mHandler.sendEmptyMessage(1);
                                    }
                                }
                            }
                        });

                    }
                });*/
                Toast.makeText(getActivity(), "正在刷新", Toast.LENGTH_SHORT).show();
            }
        });
        //设置更新时的颜色，目前感觉没有用处，未显示出来
//        mSwipeLayout.setColorSchemeColors(R.color.blue,
//                R.color.red,
//                R.color.blue,
//                R.color.red);


        //设置发布内容的适配器,更改为自己定义的adapter
        contentAdapter = new myListAdapter(getContext(), R.layout.main_listview_content, plist);
        mListView.setAdapter(contentAdapter);
        //点击list项,跳转不同页面详情
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转页面
                PublishList list = plist.get(position);
                Intent intent = new Intent(getActivity(), PublishDetailsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("userInfo", list.getUser());
                mBundle.putSerializable("publishInfo", list.getPublish());
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
        clickImgIfNotLogin();
//        init();
        return view;
    }


    private int limit = 10; // 每页的数据是10条
    private int curPage = 0; // 当前页的编号，从0开始

    private void init() {
       /* page = 0;
        BmobQuery<PublishBean> query = new BmobQuery<>();
        // 按时间降序查询
        query.order("-createdAt");
        int count = 0;
// 跳过之前页数并去掉重复数据
        query.setSkip(page);
// 设置每页数据个数
        query.setLimit(limit);
        // 查找数据
        query.findObjects(new FindListener<PublishBean>() {
            @Override
            public void done(List<PublishBean> list, BmobException e) {
                if (list.size() > 0) {
                        // 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
                        curPage = 0;
                    }
                    // 将本次查询的数据添加到plist中
                    // 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
                    curPage++;
//					 showToast("第"+(page+1)+"页数据加载完成");
                }

        });
*/


        /*mHandler.post(new Runnable() {
                          @Override
                          public void run() {

                              BmobQuery query = new BmobQuery("PublishBean");
                              //查询所有pubish,每次
                              query.setLimit(100);
//                                      query.setSkip(size);
                              //按时间排序
                              query.order("-createdAt");
                              //查询数据表 PublishBean中所有数据,获取id的user
                              query.findObjects(new FindListener<PublishBean>() {
                                  @Override
                                  public void done(List<PublishBean> list, BmobException e) {
                                      if (e == null) {
                                          for (int i = 0; i <= list.size(); i++) {
                                              final PublishBean publish = list.get(i);
                                              UserBean user = publish.getUserBean();
//                                size = i;
                                              //查询publish对应user的id,获取对应的user对象
                                              BmobQuery<UserBean> query_user = new BmobQuery<>();
                                              query_user.getObject(user.getObjectId(), new QueryListener<UserBean>() {
                                                  @Override
                                                  public void done(UserBean userBean, BmobException e) {
                                                      if (e == null) {
                                                          UserBean user = userBean;
                                                          PublishList p1 = new PublishList(publish, user);
                                                          plist.add(p1);
                                                          size++;
                                                          mHandler.sendEmptyMessage(0);
                                                      } else {
                                                          Toast.makeText(getContext(), "未找到更多数据", Toast.LENGTH_SHORT).show();
                                                      }

                                                  }
                                              });
                                          }
                                          Toast.makeText(getActivity(), "init" + list.size() + "size" + size, Toast.LENGTH_SHORT).show();
                                      }
                                  }
                              });
                          }
                      });
*/
    }

    //小头像如果未登录,跳转登录页面,登录了发出提示消息
    private void clickImgIfNotLogin() {
        main_img_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SharedPreferenceUtil.getBooleanInfo(getContext(), "isLogin")) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "您已经登录了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}


