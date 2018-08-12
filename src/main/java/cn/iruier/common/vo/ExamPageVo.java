package cn.iruier.common.vo;

import cn.iruier.entity.Paper;

import java.util.List;

public class ExamPageVo {
    private Paper paper;
    private List<QuestionVo> questionVos;

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public List<QuestionVo> getQuestionVos() {
        return questionVos;
    }

    public void setQuestionVos(List<QuestionVo> questionVos) {
        this.questionVos = questionVos;
    }
}
