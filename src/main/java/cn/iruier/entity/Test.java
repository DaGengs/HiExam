package cn.iruier.entity;

import java.util.Date;

public class Test {
    private int test_id;
    private String test_title;
    private Date createTime;
    private String user_no;
    private int status;

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public String getTest_title() {
        return test_title;
    }

    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
