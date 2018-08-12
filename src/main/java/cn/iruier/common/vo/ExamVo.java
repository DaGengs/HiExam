package cn.iruier.common.vo;

import java.util.List;

public class ExamVo {
    private String title;//试卷名称
    private List<ExamPageVo> examPageVos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ExamPageVo> getExamPageVos() {
        return examPageVos;
    }

    public void setExamPageVos(List<ExamPageVo> examPageVos) {
        this.examPageVos = examPageVos;
    }
}
