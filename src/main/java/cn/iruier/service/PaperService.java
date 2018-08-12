package cn.iruier.service;

import cn.iruier.common.vo.ExamPageVo;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.PaperVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Paper;

public interface PaperService {
    void save(Paper paper);

    ResultVo createPaper(PaperVo paperVo);

    PageVo<Paper> queryAll(int page, int size);

    PageVo<Paper> queryByStatus(int page, int size);

    ExamPageVo getExamData(int paper_id);
}
