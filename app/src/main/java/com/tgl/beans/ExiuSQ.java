package com.tgl.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by 山花烂漫 on 2017/4/26.
 */

public class ExiuSQ extends BmobObject {
    private UserBean user;//用户
    private boolean isSolutioned;//已经被处理
    private boolean isSolutioning;//正在处理
    private String sqContent;//申请内容

    public String getSqContent() {
        return sqContent;
    }

    public void setSqContent(String sqContent) {
        this.sqContent = sqContent;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isSolutioned() {
        return isSolutioned;
    }

    public void setSolutioned(boolean solutioned) {
        isSolutioned = solutioned;
    }

    public boolean isSolutioning() {
        return isSolutioning;
    }

    public void setSolutioning(boolean solutioning) {
        isSolutioning = solutioning;
    }
}
