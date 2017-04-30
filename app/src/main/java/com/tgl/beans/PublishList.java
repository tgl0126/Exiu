package com.tgl.beans;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by 山花烂漫 on 2017/4/24.
 */

public class PublishList implements Serializable {
    private PublishBean publish;
    private UserBean user;
    private ImageView userHead;
    private TextView userName;
    private  TextView publishDate;
    private TextView title;
    private TextView content;
    private TextView wherefrom;

    public PublishList(PublishBean publish, UserBean user) {
        this.publish = publish;
        this.user = user;
    }

    public PublishBean getPublish() {
        return publish;
    }

    public void setPublish(PublishBean publish) {
        this.publish = publish;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public ImageView getUserHead() {
        return userHead;
    }

    public void setUserHead(ImageView userHead) {
        this.userHead = userHead;
    }

    public TextView getUserName() {
        return userName;
    }

    public void setUserName(TextView userName) {
        this.userName = userName;
    }

    public TextView getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(TextView publishDate) {
        this.publishDate = publishDate;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getContent() {
        return content;
    }

    public void setContent(TextView content) {
        this.content = content;
    }

    public TextView getWherefrom() {
        return wherefrom;
    }

    public void setWherefrom(TextView wherefrom) {
        this.wherefrom = wherefrom;
    }
}
