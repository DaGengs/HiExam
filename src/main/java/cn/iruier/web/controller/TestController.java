package cn.iruier.web.controller;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.common.vo.TestListVo;
import cn.iruier.common.vo.TestPaperVo;
import cn.iruier.entity.User;
import cn.iruier.service.TestService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @Autowired
    private TestService service;

    @RequestMapping(value = "createTest.do", method = {RequestMethod.POST})
    @ResponseBody
    public TestPaperVo randomTest(int subj_id, String que_type, String que_level) {
        StringBuffer where = new StringBuffer();
        where.append("where subj_id =" + subj_id + " and que_type = '" + que_type + "' and que_level = '" + que_level +"'");
        return service.getTest(where.toString());
    }

    @RequestMapping("TestListqueryBypage.do")
    @ResponseBody
    public PageVo<TestListVo> queryByPage(int page, int limit) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return service.queryByUser_no(user.getUser_no(), page, limit);
    }

}
