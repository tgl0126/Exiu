package com.tgl.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by 山花烂漫 on 2017/3/17.
 */

public class PublishBean extends BmobObject{
    private Integer ID;//发布内容的ID
    private String Title;//内容标题
    private String Content;//发布内容文本,即问题描述
//    private Date CreateTime;//更新时间,NowDate-creatDate用于计算距离目前时间。Bmob自动生产,所以不需要了
//    private byte []Picture;//上传的图片
    private String WhereFrom;//来自哪儿
    private String ProblemType;//问题分类
    private boolean IsSoluting;//是否正在修
    private boolean IsSoluted;//是否已修
    private UserBean userBean;//一对多关系
    private PublishBean publishBean;

    public PublishBean getPublishBean() {
        return publishBean;
    }

    public void setPublishBean(PublishBean publishBean) {
        this.publishBean = publishBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public PublishBean() {
    }

    public PublishBean(PublishBean publishBean ,UserBean userBean) {
        this.publishBean=publishBean;
        this.userBean = userBean;
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

    public boolean isSoluting() {
        return IsSoluting;
    }

    public void setSoluting(boolean soluting) {
        IsSoluting = soluting;
    }

    public boolean isSoluted() {
        return IsSoluted;
    }

    public void setSoluted(boolean soluted) {
        IsSoluted = soluted;
    }
}
