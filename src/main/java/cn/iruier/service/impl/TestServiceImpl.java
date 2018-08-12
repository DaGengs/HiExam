package cn.iruier.service.impl;

import cn.iruier.common.utils.ListUtils;
import cn.iruier.common.utils.QuestionUtils;
import cn.iruier.common.vo.*;
import cn.iruier.dao.QuestionMapper;
import cn.iruier.dao.TestMapper;
import cn.iruier.entity.Question;
import cn.iruier.entity.Test;
import cn.iruier.entity.User;
import cn.iruier.service.TestService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper mapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public TestPaperVo getTest(String where) {
        Test test = new Test();
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        test.setUser_no(user.getUser_no());
        test.setStatus(1);
        test.setTest_title("自我测试-" + SimpleDateFormat.getInstance().format(new Date()));
        test.setCreateTime(new Date());
        mapper.save(test);

        List<Question> questions = questionMapper.query(where);
        List<Question> randomList = ListUtils.getRandomList(questions, 20);


        TestPaperVo testPaperVo = new TestPaperVo();

        testPaperVo.setTestPaperId(test.getTest_id());
        testPaperVo.setTestPaperName(test.getTest_title());

        List<QuestionVo> questionVos = QuestionUtils.questionConversion(randomList);
        testPaperVo.setQuestionVos(questionVos);
        return testPaperVo;
    }

    @Override
    public PageVo<TestListVo> queryByUser_no(String user_no, int page, int size) {
        PageVo<TestListVo> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<TestListVo> testListVos = mapper.queryByUser_no(user_no, index, size);
        if (testListVos != null) {
            pageVo.setCode(0);
            pageVo.setMsg("");
            pageVo.setCount(mapper.queryCountByNo(user_no));
            pageVo.setData(testListVos);
        } else {
            pageVo.setCode(1);
            pageVo.setMsg("暂无数据");
            pageVo.setCount(0);
            pageVo.setData(new ArrayList<>());
        }
        return pageVo;
    }

}
