package cn.iruier.web.controller;

import cn.iruier.common.vo.ExamPageVo;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.PaperVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Paper;
import cn.iruier.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaperController {

    @Autowired
    private PaperService service;

    @RequestMapping(value = "createPaper.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo createPaper(@RequestBody PaperVo paperVo) {
        return service.createPaper(paperVo);
    }

    @RequestMapping("/queryByPage.do")
    @ResponseBody
    public PageVo<Paper> queryByPage(int page, int limit) {
        return service.queryAll(page, limit);
    }

    @RequestMapping("/queryByStatus.do")
    @ResponseBody
    public PageVo<Paper> queryByStatus(int page, int limit) {
        return service.queryByStatus(page, limit);
    }

    @RequestMapping(value = "getPaperData.do", method = {RequestMethod.POST})
    @ResponseBody
    public ExamPageVo getExamData(int paper_id) {
        System.err.println(paper_id);
        return service.getExamData(paper_id);
    }
}
