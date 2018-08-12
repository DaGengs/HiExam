package cn.iruier.web.controller;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Subject;
import cn.iruier.entity.User;
import cn.iruier.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService service;

    @RequestMapping(value = "subjectAdd.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo subjectAdd(@RequestBody Subject subject, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        subject.setUser_no(user.getUser_no());
        return service.save(subject);
    }

    @RequestMapping(value = "subjectUpdate.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo subjectUpdate(@RequestBody Subject subject) {
        return service.update(subject);
    }

    @RequestMapping(value = "subjectDelete.do")
    @ResponseBody
    public ResultVo subjectDelete(int subj_id) {
        return service.delete(subj_id);
    }

    @RequestMapping("subjectQueryByPage.do")
    @ResponseBody
    public PageVo<Subject> queryByPage(int page, int limit) {
        return service.queryByPage(page, limit);
    }

    @RequestMapping("getSubject.do")
    @ResponseBody
    public List<Subject> queryAll() {
        return service.queryAll();
    }
}
