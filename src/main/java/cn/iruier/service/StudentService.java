package cn.iruier.service;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Student;

import java.util.List;

public interface StudentService {
    ResultVo save(Student student);

    ResultVo update(Student student);

    ResultVo delete(String stu_no);

    PageVo<Student> queryByPage(int page, int size, int class_no, int status);

    /*批量新增*/
    ResultVo saveBatch(List<Student> students);

    //查询个人信息
    Student queryByNo(String stu_no);


}
