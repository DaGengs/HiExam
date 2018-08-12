package cn.iruier.web.controller;

import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.User;
import cn.iruier.service.TestDetailService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestDetailController {

    @Autowired
    private TestDetailService service;

    @RequestMapping(value = "commitPage.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo commit(@RequestParam("test_id") int testPaperId, @RequestParam("answers[]")String[] answers, @RequestParam("ques_ids[]")int[] ques_ids, @RequestParam("scores[]")int[] scores) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return service.saveBatch(testPaperId, answers, ques_ids, scores, user.getUser_no());
    }
}
