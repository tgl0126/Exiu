package com.tgl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgl.beans.PublishBean;
import com.tgl.beans.PublishList;
import com.tgl.beans.UserBean;
import com.tgl.exiu.R;
import com.tgl.utils.TimeFormat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by 山花烂漫 on 2017/4/22.
 */

public class myListAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;
    private int resourceId;

    public myListAdapter(Context context, int textViewResourceId, List<PublishList> objects) {
        mContext=context;
        resourceId = textViewResourceId;
        mList=objects;
    }
    public myListAdapter(Context context, List list) {
        mContext = context;
        mList = list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public PublishList getItem(int i) {
        return (PublishList) mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        PublishList pubishBean= (PublishList) getItem(position);
        PublishList publishList = getItem(position);//获取pubulishlist
        PublishBean publish = publishList.getPublish();//获取发布信息对象
        UserBean user = publishList.getUser();//获取用户对象
        View view;
        HolderView holder = null;
        if (convertView == null) {
            holder = new HolderView();
            //修改成自己的布局
            view = LayoutInflater.from(mContext).inflate(resourceId,viewGroup,false);
            holder.img_userHead = (ImageView) view.findViewById(R.id.main_img_userHead);
            holder.tv_userName = (TextView) view.findViewById(R.id.main_tv_userName);
            holder.tv_publishDate = (TextView) view.findViewById(R.id.main_tv_publishDate);
            holder.tv_title = (TextView) view.findViewById(R.id.main_tv_title);
            holder.tv_content = (TextView) view.findViewById(R.id.main_tv_content);
            holder.tv_wherefrom = (TextView) view.findViewById(R.id.main_tv_publishcontent_wherefrom);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (HolderView) view.getTag();
        }
        if (user!=null&&publish!=null) {

//        holder.img_userHead.setImageBitmap(getbitmap(user.getHead().getFileUrl()));
            holder.tv_userName.setText(user.getUsername()==null?"未知":user.getUsername());
            holder.tv_title.setText(publish.getTitle()==null?"未知":publish.getTitle());
            holder.tv_content.setText(publish.getContent()==null?"未知":publish.getContent());
            holder.tv_publishDate.setText(publish.getCreatedAt()==null?"未知": TimeFormat.getTimeFormat(publish.getCreatedAt()));
            holder.tv_wherefrom.setText(user.getAddress()==null?"未知":user.getAddress());
        }
        return view;
    }
    class HolderView {
       ImageView img_userHead;
       TextView tv_userName;
       TextView tv_publishDate;
       TextView tv_title;
       TextView tv_content;
       TextView tv_wherefrom;
    }
    private Bitmap getbitmap(String imageUri) {
        // 显示网络上的图片
        Bitmap bitmap = null;
        try {
            URL myFileUrl = new URL(imageUri);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            bitmap = null;
        } catch (IOException e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }
}
