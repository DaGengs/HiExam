package cn.iruier.service.impl;

import cn.iruier.common.vo.ResultVo;
import cn.iruier.dao.TestDetailMapper;
import cn.iruier.dao.TestMapper;
import cn.iruier.entity.TestDetail;
import cn.iruier.service.TestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestDetailServiceImpl implements TestDetailService {

    @Autowired
    private TestDetailMapper mapper;

    @Autowired
    private TestMapper testMapper;

    @Override
    public ResultVo saveBatch(int test_id, String[] answers, int[] ques_ids, int[] scores, String user_no) {
        try {
            for (int i = 0; i < ques_ids.length; i++) {
                TestDetail testDetail = new TestDetail();
                testDetail.setTest_id(test_id);
                testDetail.setQues_id(ques_ids[i]);
                testDetail.setScore(scores[i]);
                testDetail.setUser_no(user_no);
                testDetail.setAnswer(answers[i]);
                testDetail.setCreateTime(new Date());
                mapper.save(testDetail);
            }
            testMapper.updateStatus(test_id);
            return ResultVo.setSuccess("提交成功");
        } catch (Exception e) {
            return ResultVo.setError("提交失败");
        }
    }
}
