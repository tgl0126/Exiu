package com.tgl.beans;

import java.io.File;

import cn.bmob.v3.BmobObject;

/**
 * Created by 山花烂漫 on 2017/3/28.
 */

public class UserBean extends BmobObject{
    private Integer ID;//用户ID名  固定唯一不变的 ,主键，自增
    private String Name;//用户名
    private String PWD;//用户密码
    private String Tel;//联系电话
    private String Address;//联系地址
    private boolean Sex;//性别,ture 男，false 女
    private File Head;//用户头像
    private boolean IsExiuStaff;//是否为维修人员
    private int Stars;//维修人员用户星级

    public UserBean() {
    }
}