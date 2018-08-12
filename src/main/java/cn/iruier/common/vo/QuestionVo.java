package cn.iruier.common.vo;

import java.util.List;

public class QuestionVo {
    private int que_id;
    private String que_title;
    private String que_type;
    private String que_RightAnswer;
    private List options;

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

    public List getOptions() {
        return options;
    }

    public void setOptions(List options) {
        this.options = options;
    }
}
