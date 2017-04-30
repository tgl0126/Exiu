package com.tgl.exiu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tgl.fragment.MainFragment;
import com.tgl.fragment.MesageFragment;
import com.tgl.fragment.MyInfoFragment;
import com.tgl.fragment.PublishFragment;
import com.tgl.fragment.SearchFragment;
import com.tgl.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    //    private BottomNavigationBar mBottomNavigationBar;
    private MainFragment mMainFragment;
    private MesageFragment mMesageFragment;
    private PublishFragment mPublishFragment;
    private SearchFragment mSearchFragment;
    private MyInfoFragment mMyInfoFragment;
    private Fragment fragment;//定义一个Fragment，来做为转换的
    @BindView(R.id.bottom_navigation_bar)//底部导航栏
            BottomNavigationBar mBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化Bmob
        BmobConfig config = new BmobConfig.Builder(this)
                .setApplicationId("92d9331fd3649f34dff051bb70b4ae68")
                .setConnectTimeout(30)
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
        ButterKnife.bind(this);
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBackgroundColorResource(R.color.orange)
                .setBorderWidth(0);
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.drawable.home_fill, getString(R.string.item_home)).setInactiveIconResource(R.drawable.home).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.search_fill, getString(R.string.item_search)).setInactiveIconResource(R.drawable.search).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.publish_fill, getString(R.string.item_publish)).setInactiveIconResource(R.drawable.publish).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.mesage_fill, getString(R.string.item_mesage)).setInactiveIconResource(R.drawable.mesage).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.user_fill, getString(R.string.item_user)).setInactiveIconResource(R.drawable.user).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
        setDefaultFragment();
    }

    //初始化页面
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mMainFragment = mMainFragment.newInstance("", "");
        transaction.add(R.id.mMainFrame, mMainFragment);//初始化页面
        transaction.commit();
        fragment = mMainFragment;
    }


    //切换Fragment页面方法
    public void switchContent(Fragment to) {
        if (fragment != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(fragment).add(R.id.mMainFrame, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(fragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            fragment = to;
        }

    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance("mnt", "match_parent");
                }
                switchContent(mMainFragment);

                break;
            case 1:
                if (mSearchFragment == null) {
                    mSearchFragment = SearchFragment.newInstance("SearchFragment", "SearchFragment");
                }
                switchContent(mSearchFragment);

                break;
            case 2:
                if (mPublishFragment == null) {
                    mPublishFragment = PublishFragment.newInstance("publish", "publish");
                }
                switchContent(mPublishFragment);
                break;
            case 3:
                if (mMesageFragment == null) {
                    mMesageFragment = MesageFragment.newInstance("match_parent", "match_parent");
                }
                switchContent(mMesageFragment);
                break;
            case 4:
                if (mMyInfoFragment == null) {
                    mMyInfoFragment = MyInfoFragment.newInstance("1", "1");
                }
                if (SharedPreferenceUtil.getBooleanInfo(MainActivity.this, "isLogin")) {
                    switchContent(mMyInfoFragment);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("登录后可以解锁更多姿势哟~");
                    builder.setTitle("是否登录?");
                    builder.setNegativeButton("暂不登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switchContent(mMyInfoFragment);
                        }
                    });
                    builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.create();
                    builder.show();
                }
                break;
        }
        beginTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {

    }

    //退出事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            builder.setTitle("是否要退出应用?").setIcon(R.drawable.cry)
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
        }
        builder.show();
        return super.onKeyDown(keyCode, event);
    }
}
