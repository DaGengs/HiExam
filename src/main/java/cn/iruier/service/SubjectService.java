package cn.iruier.service;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Subject;

import java.util.List;

public interface SubjectService {
    /*新增*/
    ResultVo save(Subject subject);

    /*修改*/
    ResultVo update(Subject subject);

    /*删除*/
    ResultVo delete(int subj_id);

    /*分页查询*/
    PageVo<Subject> queryByPage(int page, int size);

    /*查询全部*/
    List<Subject> queryAll();

}
