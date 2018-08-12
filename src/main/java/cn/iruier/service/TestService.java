package cn.iruier.service;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.TestListVo;
import cn.iruier.common.vo.TestPaperVo;

public interface TestService {
    TestPaperVo getTest(String where);

    PageVo<TestListVo> queryByUser_no(String user_no, int page, int size);

}
