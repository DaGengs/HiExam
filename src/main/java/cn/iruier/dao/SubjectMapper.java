package cn.iruier.dao;

import cn.iruier.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectMapper {
    /*新增*/
    int save(Subject subject);

    /*修改*/
    int update(Subject subject);

    /*删除*/
    int delete(int subj_id);

    /*分页查询*/
    List<Subject> queryByPage(@Param("index")int index, @Param("size")int size);

    /*查询全部*/
    List<Subject> queryAll();

    /*查询数量*/
    int queryCount();


}
