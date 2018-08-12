package cn.iruier.service.impl;

import cn.iruier.common.utils.StringUtils;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.dao.StudentMapper;
import cn.iruier.entity.Student;
import cn.iruier.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper mapper;

    @Override
    public ResultVo save(Student student) {
        if (mapper.save(student) > 0) {
            return ResultVo.setSuccess("新增成功");
        } else {
            return ResultVo.setError("新增失败");
        }
    }

    @Override
    public ResultVo update(Student student) {
        if (mapper.update(student) > 0) {
            return ResultVo.setSuccess("修改成功");
        } else {
            return ResultVo.setError("修改失败");
        }
    }

    @Override
    public ResultVo delete(String stu_no) {
        if (StringUtils.empty(stu_no)) {
            if (mapper.delete(stu_no) > 0) {
                return ResultVo.setSuccess("删除成功");
            } else {
                return ResultVo.setError("删除失败");
            }
        }
        return ResultVo.setError("删除失败");
    }

    @Override
    public PageVo<Student> queryByPage(int page, int size, int class_no, int status) {
        StringBuffer where = new StringBuffer();
        where.append("where s.status =" + status);
        if (class_no != 0) {
            where.append(" and c.class_id =" + class_no);
        }
        PageVo<Student> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<Student> students = mapper.queryByPage(index, size, where.toString());
        if (students != null) {
            pageVo.setCode(0);
            pageVo.setMsg("");
            pageVo.setCount(mapper.queryCount());
            pageVo.setData(students);
        } else {
            pageVo.setData(new ArrayList<>());
            pageVo.setCount(0);
            pageVo.setMsg("暂无数据");
            pageVo.setCode(1);
        }
        return pageVo;
    }

    @Override
    public ResultVo saveBatch(List<Student> students) {
        try {
            for (Student student : students) {
                mapper.save(student);
            }
            return ResultVo.setSuccess("导入成功");
        } catch (Exception e) {
            return ResultVo.setError("导入失败");
        }
    }

    @Override
    public Student queryByNo(String stu_no) {
        return mapper.queryByNo(stu_no);
    }

}
