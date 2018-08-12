package cn.iruier.service;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Teacher;

public interface TeacherService {
    ResultVo save(Teacher teacher);

    ResultVo update(Teacher teacher);

    ResultVo delete(String tch_no);

    PageVo<Teacher> queryByPage(int page, int size);

}
