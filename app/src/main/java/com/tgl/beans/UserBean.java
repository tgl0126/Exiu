package com.tgl.beans;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 山花烂漫 on 2017/3/28.
 */

public class UserBean extends BmobUser {
    private Integer ID;//用户ID名  固定唯一不变的 ,主键，自增
//    private String Name;//用户名
//    private String PWD;//用户密码
//    private String Tel;//联系电话,唯一
    private String Address;//联系地址
    private boolean Sex;//性别,ture 男，false 女
    private BmobFile Head;//用户头像
    private boolean IsExiuStaff;//是否为维修人员
    private int Stars;//维修人员用户星级
    private String PersonalSign;

    public String getPersonalSign() {
        return PersonalSign;
    }

    public void setPersonalSign(String personalSign) {
        PersonalSign = personalSign;
    }
    //    private boolean IsLogin;//是否已登录

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public String getPWD() {
//        return PWD;
//    }
//
//    public void setPWD(String PWD) {
//        this.PWD = PWD;
//    }

//    public String getTel() {
//        return Tel;
//    }
//
//    public void setTel(String tel) {
//        Tel = tel;
//    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isSex() {
        return Sex;
    }

    public void setSex(boolean sex) {
        Sex = sex;
    }

    public BmobFile getHead() {
        return Head;
    }

    public void setHead(BmobFile head) {
        Head = head;
    }

    public boolean isExiuStaff() {
        return IsExiuStaff;
    }

    public void setExiuStaff(boolean exiuStaff) {
        IsExiuStaff = exiuStaff;
    }

    public int getStars() {
        return Stars;
    }

    public void setStars(int stars) {
        Stars = stars;
    }


}