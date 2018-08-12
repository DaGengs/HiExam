package cn.iruier.dao;

import cn.iruier.common.vo.CountVo;
import cn.iruier.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    /*新增*/
    int save(Question question);

    /*修改*/
    int update(Question question);

    /*删除*/
    int delete(int que_id);

    /*分页查询*/
    List<Question> queryByPage(@Param("index")int index, @Param("size")int size);

    /*查询全部*/
    List<Question> queryAll();

    /*查询数量*/
    int queryCount();

    List<Question> query(@Param("where") String where);

    List<CountVo> queryNum(int subj_id);

    List<Question> queryByPaperId(int paper_id);
}
