package cn.iruier.service;

import cn.iruier.common.vo.CountVo;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.QuestionVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Question;

import java.util.List;

public interface QuestionService {
    /*新增*/
    ResultVo save(Question question);

    /*修改*/
    ResultVo update(Question question);

    /*删除*/
    ResultVo delete(int que_id);

    /*分页查询*/
    PageVo<Question> queryByPage(int page, int size);

/*
     List<QuestionVo> queryQues(String where);*/

    /*批量新增*/
    ResultVo saveBatch(List<Question> questions);

    List<CountVo> querynum(int subj_id);
}
