package com.tgl.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by 山花烂漫 on 2017/4/29.
 */

public class JYYFK extends BmobObject {
    private UserBean user;
    private String textCentent;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getTextCentent() {
        return textCentent;
    }

    public void setTextCentent(String textCentent) {
        this.textCentent = textCentent;
    }
}
