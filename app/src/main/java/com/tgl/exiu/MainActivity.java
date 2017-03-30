package com.tgl.exiu;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    private MainFragment mMainFragment;
    private MesageFragment mMesageFragment;
    private PublishFragment mPublishFragment;
    private SearchFragment mSearchFragment;
    private MyInfoFragment mMyInfoFragment;
    private ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/**
 * Create by 山花烂漫
 * on 2017/3/22
 * at 11:26
 *  初始化bmob
*/
//        BmobConfig config =new BmobConfig.Builder(this)
//        //设置appkey
//        .setApplicationId("Your Application ID")
//        //请求超时时间（单位为秒）：默认15s
//        .setConnectTimeout(30)
//        //文件分片上传时每片的大小（单位字节），默认512*1024
//        .setUploadBlockSize(1024*1024)
//        //文件的过期时间(单位为秒)：默认1800s
//        .setFileExpiration(2500)
//        .build();
//        Bmob.initialize(config);

        ButterKnife.bind(this);
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBackgroundColorResource(R.color.orange)
                .setBorderWidth(0);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.drawable.home_fill, getString(R.string.item_home)).setInactiveIconResource(R.drawable.home).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.search_fill, getString(R.string.item_search)).setInactiveIconResource(R.drawable.search).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.publish_fill, getString(R.string.item_publish)).setInactiveIconResource(R.drawable.publish).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.mesage_fill, getString(R.string.item_mesage)).setInactiveIconResource(R.drawable.mesage).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.user_fill, getString(R.string.item_user)).setInactiveIconResource(R.drawable.user).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();

    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        mMainFragment=mMainFragment.newInstance("1","1");
        transaction.replace(R.id.mMainFrame, mMainFragment);
        transaction.commit();
    }

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

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance("mnt", "match_parent");
                }
                beginTransaction.replace(R.id.mMainFrame, mMainFragment);
                break;
            case 1:
                if (mSearchFragment == null) {
                    mSearchFragment = SearchFragment.newInstance("SearchFragment", "SearchFragment");
                }
                beginTransaction.replace(R.id.mMainFrame, mSearchFragment);
                break;
            case 2:
                if (mPublishFragment == null) {
                    mPublishFragment = PublishFragment.newInstance("publish", "publish");
                }
                beginTransaction.replace(R.id.mMainFrame, mPublishFragment);
                break;
            case 3:
                if (mMesageFragment == null) {
                    mMesageFragment = MesageFragment.newInstance("match_parent", "match_parent");
                }
                beginTransaction.replace(R.id.mMainFrame, mMesageFragment);
                break;
            case 4:
                if (mMyInfoFragment == null) {
                    mMyInfoFragment = MyInfoFragment.newInstance("match_parent", "match_parent");
                }
                beginTransaction.replace(R.id.mMainFrame, mMyInfoFragment);
        }
        beginTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {

    }
}
