package cn.iruier.web.controller;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Teacher;
import cn.iruier.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService service;

    @RequestMapping(value = "teacherAdd.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo studentAdd(@RequestBody Teacher teacher) {
        return service.save(teacher);
    }

    @RequestMapping(value = "teacherUpdate.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo studentUpdate(@RequestBody Teacher teacher) {
        return service.update(teacher);
    }

    @RequestMapping("teacherDelete.do")
    @ResponseBody
    public ResultVo studentDelete(String tch_no) {
        return service.delete(tch_no);
    }

    @RequestMapping("teacherQueryByPage.do")
    @ResponseBody
    public PageVo<Teacher> queryByPage(int page, int limit) {
        return service.queryByPage(page, limit);
    }

}
