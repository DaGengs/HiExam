package cn.iruier.common.utils;

import cn.iruier.common.vo.QuestionVo;
import cn.iruier.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionUtils {
    public static List<QuestionVo> questionConversion(List<Question> questions) {
        List<QuestionVo> questionVos = new ArrayList<>();

        /*试题选项转换*/
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQue_id(question.getQue_id());
            questionVo.setQue_title(question.getQue_title());
            questionVo.setQue_type(question.getQue_type());
            questionVo.setQue_RightAnswer(question.getQue_RightAnswer());
            List options = new ArrayList();
            options.add(question.getQue_AnswerA());
            options.add(question.getQue_AnswerB());
            options.add(question.getQue_AnswerC());
            options.add(question.getQue_AnswerD());
            options.add(question.getQue_AnswerE());
            options.add(question.getQue_AnswerF());
            questionVo.setOptions(options);
            questionVos.add(questionVo);
        }
        return questionVos;
    }
}
