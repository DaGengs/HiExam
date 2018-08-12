package cn.iruier.dao;

import cn.iruier.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int save(Student student);

    int update(Student student);

    int delete(String stu_no);

    int queryCount();

    List<Student> queryByPage(@Param("index")int index, @Param("size")int size, @Param("where") String where);

    //个人信息
    Student  queryByNo(String stu_no);

}
