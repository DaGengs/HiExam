package cn.iruier.web.controller;

import cn.iruier.common.utils.ExcelUtils;
import cn.iruier.common.vo.CountVo;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.QuestionVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Question;
import cn.iruier.entity.User;
import cn.iruier.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService service;

    @RequestMapping(value = "questionAdd.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo questionAdd(@RequestBody Question question, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        question.setUser_no(user.getUser_no());
        return service.save(question);
    }

    @RequestMapping(value = "questionUpdate.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo questionUpdate(@RequestBody Question question) {
        return service.update(question);
    }

    @RequestMapping(value = "questionDelete.do")
    @ResponseBody
    public ResultVo questionDelete(int que_id) {
        return service.delete(que_id);
    }

    @RequestMapping("questionQueryByPage.do")
    @ResponseBody
    public PageVo<Question> queryByPage(int page, int limit) {
        return service.queryByPage(page, limit);
    }

    @RequestMapping(value = "questionAddBatch.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo addBatch(MultipartFile file, int subj_id, int status, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Question> questions = ExcelUtils.parseExcel(file.getInputStream(), user.getUser_no(), subj_id, status);
        return service.saveBatch(questions);
    }

    @RequestMapping("getNumber.do")
    @ResponseBody
    public List<CountVo> getNumber(int subj_id) {
        return service.querynum(subj_id);
    }
}
