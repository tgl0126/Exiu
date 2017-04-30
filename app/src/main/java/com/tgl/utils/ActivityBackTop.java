package com.tgl.utils;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.tgl.exiu.R;

/**
 * Created by 山花烂漫 on 2017/4/26.
 */

public class ActivityBackTop{
    private static Activity mActivity;
    public static void getCustomTitle(Activity activity, String title) {
        mActivity = activity;
        mActivity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        mActivity.setContentView(R.layout.view_top);
        mActivity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.view_top);

        TextView textView = (TextView) activity.findViewById(R.id.toolbar_title);
        textView.setText(title);
        Button titleBackBtn = (Button) activity.findViewById(R.id.toolbar_back);
        titleBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }


}