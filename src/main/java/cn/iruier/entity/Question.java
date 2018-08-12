package cn.iruier.entity;

import java.util.Date;

public class Question {
    private int que_id;
    private String que_title;
    private String que_AnswerA;
    private String que_AnswerB;
    private String que_AnswerC;
    private String que_AnswerD;
    private String que_AnswerE;
    private String que_AnswerF;
    private String que_type;
    private String que_RightAnswer;
    private String que_level;
    private Date createTime;
    private String user_no;
    private String subj_name;
    private int subj_id;
    private int status;

    public int getQue_id() {
        return que_id;
    }

    public void setQue_id(int que_id) {
        this.que_id = que_id;
    }

    public String getQue_title() {
        return que_title;
    }

    public void setQue_title(String que_title) {
        this.que_title = que_title;
    }

    public String getQue_AnswerA() {
        return que_AnswerA;
    }

    public void setQue_AnswerA(String que_AnswerA) {
        this.que_AnswerA = que_AnswerA;
    }

    public String getQue_AnswerB() {
        return que_AnswerB;
    }

    public void setQue_AnswerB(String que_AnswerB) {
        this.que_AnswerB = que_AnswerB;
    }

    public String getQue_AnswerC() {
        return que_AnswerC;
    }

    public void setQue_AnswerC(String que_AnswerC) {
        this.que_AnswerC = que_AnswerC;
    }

    public String getQue_AnswerD() {
        return que_AnswerD;
    }

    public void setQue_AnswerD(String que_AnswerD) {
        this.que_AnswerD = que_AnswerD;
    }

    public String getQue_AnswerE() {
        return que_AnswerE;
    }

    public void setQue_AnswerE(String que_AnswerE) {
        this.que_AnswerE = que_AnswerE;
    }

    public String getQue_AnswerF() {
        return que_AnswerF;
    }

    public void setQue_AnswerF(String que_AnswerF) {
        this.que_AnswerF = que_AnswerF;
    }

    public String getQue_type() {
        return que_type;
    }

    public void setQue_type(String que_type) {
        this.que_type = que_type;
    }

    public String getQue_RightAnswer() {
        return que_RightAnswer;
    }

    public void setQue_RightAnswer(String que_RightAnswer) {
        this.que_RightAnswer = que_RightAnswer;
    }

    public String getQue_level() {
        return que_level;
    }

    public void setQue_level(String que_level) {
        this.que_level = que_level;
    }

    public int getSubj_id() {
        return subj_id;
    }

    public void setSubj_id(int subj_id) {
        this.subj_id = subj_id;
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

    public String getSubj_name() {
        return subj_name;
    }

    public void setSubj_name(String subj_name) {
        this.subj_name = subj_name;
    }

}
