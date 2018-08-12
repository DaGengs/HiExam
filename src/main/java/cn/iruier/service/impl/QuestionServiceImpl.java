package cn.iruier.service.impl;

import cn.iruier.common.vo.CountVo;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.QuestionVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.dao.QuestionMapper;
import cn.iruier.entity.Question;
import cn.iruier.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper mapper;

    @Override
    public ResultVo save(Question question) {
        question.setCreateTime(new Date());
        if (mapper.save(question) > 0) {
            return ResultVo.setSuccess("新增成功");
        } else {
            return ResultVo.setError("新增失败");
        }
    }

    @Override
    public ResultVo update(Question question) {
        if (mapper.update(question) > 0) {
            return ResultVo.setSuccess("修改成功");
        } else {
            return ResultVo.setError("修改失败");
        }
    }

    @Override
    public ResultVo delete(int que_id) {
        if (que_id != 0) {
            if (mapper.delete(que_id) > 0) {
                return ResultVo.setSuccess("删除成功");
            } else {
                return ResultVo.setError("删除失败");
            }
        }
        return ResultVo.setError("删除失败");
    }

    @Override
    public PageVo<Question> queryByPage(int page, int size) {
        PageVo<Question> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<Question> questions = mapper.queryByPage(index, size);
        if (questions != null) {
            pageVo.setCode(0);
            pageVo.setCount(mapper.queryCount());
            pageVo.setMsg("");
            pageVo.setData(questions);
        } else {
            pageVo.setCode(1);
            pageVo.setCount(0);
            pageVo.setMsg("暂无数据");
            pageVo.setData(new ArrayList<>());
        }
        return pageVo;
    }

    @Override
    public ResultVo saveBatch(List<Question> questions) {
        try {
            for (Question question : questions) {
                mapper.save(question);
            }
            return ResultVo.setSuccess("导入成功");
        } catch (Exception e) {
            return ResultVo.setError("导入失败");
        }
    }

    @Override
    public List<CountVo> querynum(int subj_id) {
        if (subj_id != 0) {
            return mapper.queryNum(subj_id);
        } else {
            return new ArrayList<>();
        }
    }
}
