package cn.iruier.common.vo;

import java.util.Date;
import java.util.List;

public class PaperVo {
    /*试卷名称*/
    private String title;
    /*考试时长*/
    private int time;
    /*所属题库*/
    private int subj_id;
    /*开考时间*/
    private Date startTime;
    /*结束时间*/
    private Date endTime;
    /*试卷状态*/
    private int status;
    /*题型设置*/
    private List<ConditionVo> conditionVos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSubj_id() {
        return subj_id;
    }

    public void setSubj_id(int subj_id) {
        this.subj_id = subj_id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ConditionVo> getConditionVos() {
        return conditionVos;
    }

    public void setConditionVos(List<ConditionVo> conditionVos) {
        this.conditionVos = conditionVos;
    }

    @Override
    public String toString() {
        return "PaperVo{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", conditionVos=" + conditionVos +
                '}';
    }
}
