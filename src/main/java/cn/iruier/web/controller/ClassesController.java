package cn.iruier.web.controller;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Classes;
import cn.iruier.entity.User;
import cn.iruier.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ClassesController {

    @Autowired
    private ClassesService service;

    @RequestMapping(value = "classesAdd.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo classesAdd(@RequestBody Classes classes, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        classes.setUser_no(user.getUser_no());
        classes.setCreateTime(new Date());
        return service.save(classes);
    }

    @RequestMapping("classesUpdete.do")
    @ResponseBody
    public ResultVo classesUpdate(String class_name, int class_id) {
        return service.update(class_name, class_id);
    }

    @RequestMapping("classesDelete.do")
    @ResponseBody
    public ResultVo classesDelete(int class_id) {
        return service.delete(class_id);
    }

    @RequestMapping("classesQueryByPage.do")
    @ResponseBody
    public PageVo<Classes> queryByPage(int page, int limit) {
        return service.queryByPage(page, limit);
    }

    @RequestMapping("classesQueryAll.do")
    @ResponseBody
    public List<Classes> queryAll() {
        return service.queryAll();
    }
}
