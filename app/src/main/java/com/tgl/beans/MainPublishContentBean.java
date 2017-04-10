package com.tgl.beans;

import java.util.Date;

import cn.bmob.v3.BmobObject;

/**
 * Created by 山花烂漫 on 2017/3/17.
 */

public class MainPublishContentBean extends BmobObject{
    private Integer UserID;//用户ID    、、删除
    private Integer ID;//发布内容的ID
    private String Title;//内容标题
    private String Content;//发布内容文本,即问题描述
    private Date CreateTime;//更新时间,NowDate-creatDate用于计算距离目前时间。
//    private byte []Picture;//上传的图片
    private String WhereFrom;//来自哪儿
    private String ProblemType;//问题分类
    private boolean IsSoluting;//是否正在修
    private boolean IsSoluted;//是否已修
    private UserBean userBean;//一对多关系

    public MainPublishContentBean() {
    }

    public MainPublishContentBean(Integer userID, Integer ID, String title, String content, Date createTime, String whereFrom, String problemType, boolean isSoluting, boolean isSoluted) {
        UserID = userID;
        this.ID = ID;
        Title = title;
        Content = content;
        CreateTime = createTime;
        WhereFrom = whereFrom;
        ProblemType = problemType;
        IsSoluting = isSoluting;
        IsSoluted = isSoluted;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getWhereFrom() {
        return WhereFrom;
    }

    public void setWhereFrom(String whereFrom) {
        WhereFrom = whereFrom;
    }

    public String getProblemType() {
        return ProblemType;
    }

    public void setProblemType(String problemType) {
        ProblemType = problemType;
    }

    public boolean getIsSoluting() {
        return IsSoluting;
    }

    public void setIsSoluting(boolean isSoluting) {
        IsSoluting = isSoluting;
    }

    public boolean getIsSoluted() {
        return IsSoluted;
    }

    public void setIsSoluted(boolean isSoluted) {
        IsSoluted = isSoluted;
    }
}
