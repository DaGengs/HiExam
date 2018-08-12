package cn.iruier.entity;

import java.util.Date;

public class Paper {
    private int id;
    private String title;
    private Date startTime;
    private Date endTime;
    private String user_no;
    private Date createTime;
    private int time;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", user_no='" + user_no + '\'' +
                ", createTime=" + createTime +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
