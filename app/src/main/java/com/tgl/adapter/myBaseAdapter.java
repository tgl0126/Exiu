/*
package com.tgl.impl;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tgl.exiu.R;

import java.util.List;
import java.util.Objects;

*/
/**
 * Created by 山花烂漫 on 2017/3/15.
 *//*


public class myBaseAdapter extends BaseAdapter {

    private List<Fragment> fragment; //数据
    private int resource;   //item的布局
    private Context context;
    private LayoutInflater inflator;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView idTextView;
    */
/**
     *
     * @param context mainActivity
     * @param persons   显示的数据
     * @param resource  一个Item的布局
     *//*

    public myBaseAdapter(Context context, List<Object>persons, int resource){
        this.context = context;
        this.persons = persons;
        this.resource = resource;
    }
    */
/*
     * 获得数据总数
     * *//*

    @Override
    public int getCount() {
        return persons.size();
    }
    */
/*
     * 根据索引为position的数据
     * *//*

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }
    */
/*
     * 根据索引值获得Item的Id
     * *//*

    @Override
    public long getItemId(int position) {
        return position;
    }
    */
/*
     *通过索引值position将数据映射到视图
     *convertView具有缓存功能，在第一页时为null，在第二第三....页时不为null
     * *//*

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(resource, null);
        }
    }
}
*/
