package com.swjtu.survey.bean;

import java.io.Serializable;

/**
 * The data of Survey-Project,like "xx铁路测量工程"
 */
public class SurveyProjectBean implements Serializable {
    private String projectName;
    private String id;
    private String createTime;
    private String updateTime;
    private String cover;
    private String localPath;
    /*项目是否初始化(是否初始化测量参数)*/
    private boolean isProjectInit;

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isProjectInit() {
        return isProjectInit;
    }

    public void setProjectInit(boolean projectInit) {
        isProjectInit = projectInit;
    }
}
