package cn.iruier.service.impl;

import cn.iruier.common.utils.StringUtils;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.dao.TeacherMapper;
import cn.iruier.entity.Student;
import cn.iruier.entity.Teacher;
import cn.iruier.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper mapper;

    @Override
    public ResultVo save(Teacher teacher) {
        if (mapper.save(teacher) > 0) {
            return ResultVo.setSuccess("新增成功");
        } else {
            return ResultVo.setError("新增失败");
        }
    }

    @Override
    public ResultVo update(Teacher teacher) {
        if (mapper.update(teacher) > 0) {
            return ResultVo.setSuccess("修改成功");
        } else {
            return ResultVo.setError("修改失败");
        }
    }

    @Override
    public ResultVo delete(String tch_no) {
        if (StringUtils.empty(tch_no)) {
            if (mapper.delete(tch_no) > 0) {
                return ResultVo.setSuccess("删除成功");
            } else {
                return ResultVo.setError("删除失败");
            }
        }
        return ResultVo.setError("删除失败");
    }

    @Override
    public PageVo<Teacher> queryByPage(int page, int size) {
        PageVo<Teacher> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<Teacher> teachers = mapper.queryByPage(index, size);
        if (teachers != null) {
            pageVo.setCode(0);
            pageVo.setMsg("");
            pageVo.setCount(mapper.queryCount());
            pageVo.setData(teachers);
        } else {
            pageVo.setData(new ArrayList<>());
            pageVo.setCount(0);
            pageVo.setMsg("暂无数据");
            pageVo.setCode(1);
        }
        return pageVo;
    }
}
