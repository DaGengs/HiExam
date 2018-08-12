package cn.iruier.service.impl;

import cn.iruier.common.utils.ListUtils;
import cn.iruier.common.utils.QuestionUtils;
import cn.iruier.common.vo.*;
import cn.iruier.dao.PaperDetailMapper;
import cn.iruier.dao.PaperMapper;
import cn.iruier.dao.QuestionMapper;
import cn.iruier.entity.Paper;
import cn.iruier.entity.PaperDetail;
import cn.iruier.entity.Question;
import cn.iruier.entity.User;
import cn.iruier.service.PaperService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper mapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private PaperDetailMapper paperDetailMapper;

    @Override
    public void save(Paper paper) {
        mapper.save(paper);
    }

    @Override
    public ResultVo createPaper(PaperVo paperVo) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        Paper paper = new Paper();
        paper.setTitle(paperVo.getTitle());
        paper.setStartTime(paperVo.getStartTime());
        paper.setEndTime(paperVo.getEndTime());
        paper.setStatus(paperVo.getStatus());
        paper.setTime(paperVo.getTime());
        paper.setCreateTime(new Date());
        paper.setUser_no(user.getUser_no());
        /*保存试卷信息---ps:不安全*/
        mapper.save(paper);
        /*题型条件*/
        List<ConditionVo> conditionVos = paperVo.getConditionVos();
        /*试卷展示类*/
        ExamVo examVo = new ExamVo();
        examVo.setTitle(paperVo.getTitle());

        List<PaperDetail> paperDetails = new ArrayList<>();

        /*根据条件查询试题*/
        for (ConditionVo conditionVo : conditionVos) {
            /*查询条件*/
            StringBuffer where = new StringBuffer();
            where.append("where subj_id =" + paperVo.getSubj_id() + " and que_type = '" + conditionVo.getType() + "' and que_level = '" + conditionVo.getLevel() +"'");
            /*查询结果*/
            List<Question> questions = questionMapper.query(where.toString());
            /*处理结果不重复抽题*/
            List<Question> list = ListUtils.getRandomList(questions, conditionVo.getNum());
            for (Question question : list) {
                PaperDetail paperDetail = new PaperDetail();
                paperDetail.setPaper_id(paper.getId());
                paperDetail.setQues_id(question.getQue_id());
                paperDetail.setScore(conditionVo.getScore());
                paperDetails.add(paperDetail);
                System.err.println(question);
            }
            /*试题选项转换*/
            /*List<QuestionVo> questionVos = QuestionUtils.questionConversion(list);

            List<ExamPageVo> examPageVos = new ArrayList<>();
            ExamPageVo examPageVo = new ExamPageVo();
            examPageVo.setType(conditionVo.getType());
            examPageVo.setQuestionVos(questionVos);
            examPageVos.add(examPageVo);

            examVo.setExamPageVos(examPageVos);*/
        }
        try {
            for (PaperDetail paperDetail : paperDetails) {
                paperDetailMapper.save(paperDetail);
            }

            return ResultVo.setSuccess("创建成功");
        } catch (Exception e) {
            return ResultVo.setError("创建失败");
        }
    }

    @Override
    public PageVo<Paper> queryAll(int page, int size) {
        PageVo<Paper> pageVo = new PageVo<>();
        int index = 0;
        if (page> 0) {
            index = (page - 1) * size;
        }
        List<Paper> papers = mapper.queryByPage(index, size);
        System.out.println(papers.size());
        if (papers != null) {
            pageVo.setCode(0);
            pageVo.setData(papers);
            pageVo.setCount(mapper.queryCount());
            pageVo.setMsg("");
        } else {
            pageVo.setMsg("暂无数据");
            pageVo.setCount(0);
            pageVo.setData(new ArrayList<>());
            pageVo.setCode(1);
        }
        return pageVo;
    }

    @Override
    public PageVo<Paper> queryByStatus(int page, int size) {
        PageVo<Paper> pageVo = new PageVo<>();
        int index = 0;
        if (page> 0) {
            index = (page - 1) * size;
        }
        List<Paper> papers = mapper.queryByStatus(index, size);
        System.out.println(papers.size());
        if (papers != null) {
            pageVo.setCode(0);
            pageVo.setData(papers);
            pageVo.setCount(mapper.queryCountByStatus());
            pageVo.setMsg("");
        } else {
            pageVo.setMsg("暂无数据");
            pageVo.setCount(0);
            pageVo.setData(new ArrayList<>());
            pageVo.setCode(1);
        }
        return pageVo;
    }

    @Override
    public ExamPageVo getExamData(int paper_id) {
        ExamPageVo examPageVo = new ExamPageVo();
        if (paper_id > 0) {
            Paper paper = mapper.queryByPaperId(paper_id);
            List<Question> questions = questionMapper.queryByPaperId(paper_id);
            List<QuestionVo> questionVos = QuestionUtils.questionConversion(questions);
            examPageVo.setPaper(paper);
            examPageVo.setQuestionVos(questionVos);
        } else {
            return null;
        }
        return examPageVo;
    }
}
