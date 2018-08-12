package cn.iruier.common.vo;

import java.util.List;

public class TestPaperVo {
    private int testPaperId;
    private String testPaperName;
    private List<QuestionVo> questionVos;

    public int getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(int testPaperId) {
        this.testPaperId = testPaperId;
    }

    public String getTestPaperName() {
        return testPaperName;
    }

    public void setTestPaperName(String testPaperName) {
        this.testPaperName = testPaperName;
    }

    public List<QuestionVo> getQuestionVos() {
        return questionVos;
    }

    public void setQuestionVos(List<QuestionVo> questionVos) {
        this.questionVos = questionVos;
    }
}
