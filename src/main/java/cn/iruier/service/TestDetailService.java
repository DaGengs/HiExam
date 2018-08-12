package cn.iruier.service;

import cn.iruier.common.vo.ResultVo;

public interface TestDetailService {
    ResultVo saveBatch(int test_id, String[] answers, int[] ques_ids, int[] scores, String user_no);
}
