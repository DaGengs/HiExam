package cn.iruier.service.impl;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.dao.SubjectMapper;
import cn.iruier.entity.Subject;
import cn.iruier.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper mapper;

    @Override
    public ResultVo save(Subject subject) {
//        subject.setCreateTime(new Date());
        if (mapper.save(subject) > 0) {
            return ResultVo.setSuccess("新增成功");
        } else {
            return ResultVo.setError("新增失败");
        }
    }

    @Override
    public ResultVo update(Subject subject) {
        if (mapper.update(subject) > 0) {
            return ResultVo.setSuccess("修改成功");
        } else {
            return ResultVo.setError("修改失败");
        }
    }

    @Override
    public ResultVo delete(int subj_id) {
        if (subj_id != 0) {
            if (mapper.delete(subj_id) > 0) {
                return ResultVo.setSuccess("删除成功");
            } else {
                return ResultVo.setError("删除失败");
            }
        }
        return ResultVo.setError("删除失败");
    }

    @Override
    public PageVo<Subject> queryByPage(int page, int size) {
        PageVo<Subject> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<Subject> subjects = mapper.queryByPage(index, size);
        if (subjects.get(0).getSubj_name() != null) {
            pageVo.setCode(0);
            pageVo.setCount(mapper.queryCount());
            pageVo.setMsg("");
            pageVo.setData(subjects);
        } else {
            pageVo.setCode(1);
            pageVo.setCount(0);
            pageVo.setMsg("暂无数据");
            pageVo.setData(new ArrayList<>());
        }
        return pageVo;
    }

    @Override
    public List<Subject> queryAll() {
        return mapper.queryAll();
    }
}
