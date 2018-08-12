package cn.iruier.entity;

import java.util.Date;

public class Subject {
    private int subj_id;
    private String subj_name;
    private String user_no;
    private Date createTime;
    private int tatol;
    private int status;
    private String info;

    public int getSubj_id() {
        return subj_id;
    }

    public void setSubj_id(int subj_id) {
        this.subj_id = subj_id;
    }

    public String getSubj_name() {
        return subj_name;
    }

    public void setSubj_name(String subj_name) {
        this.subj_name = subj_name;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getTatol() {
        return tatol;
    }

    public void setTatol(int tatol) {
        this.tatol = tatol;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
