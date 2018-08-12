package cn.iruier.web.controller;

import cn.iruier.common.utils.ExcelUtils;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Student;
import cn.iruier.entity.User;
import cn.iruier.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @RequestMapping(value = "studentAdd.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo studentAdd(@RequestBody Student student) {
        return service.save(student);
    }

    @RequestMapping(value = "studentUpdate.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo studentUpdate(@RequestBody Student student) {
        return service.update(student);
    }

    @RequestMapping("studentDelete.do")
    @ResponseBody
    public ResultVo studentDelete(String stu_no) {
        return service.delete(stu_no);
    }

    @RequestMapping("studentQueryByPage.do")
    @ResponseBody
    public PageVo<Student> queryByPage(int page, int limit, @RequestParam(defaultValue = "0")int class_no, @RequestParam(defaultValue = "1") int status) {
        return service.queryByPage(page, limit, class_no, status);
    }

    @RequestMapping(value = "studentAddBatch.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo addBatch(MultipartFile file, int class_no, int status) throws IOException {
        List<Student> students = ExcelUtils.parseExcelS(file.getInputStream(), class_no, status);
        return service.saveBatch(students);
    }

    @RequestMapping(value = "studentQueryByNo.do")
    @ResponseBody
    public Student queryByNo() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return service.queryByNo(user.getUser_no());
    }

}
