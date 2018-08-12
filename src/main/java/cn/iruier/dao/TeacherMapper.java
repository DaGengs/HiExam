package cn.iruier.dao;

import cn.iruier.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int save(Teacher teacher);

    int update(Teacher teacher);

    int delete(String tch_no);

    int queryCount();

    List<Teacher> queryByPage(@Param("index") int index, @Param("size") int size);

}
