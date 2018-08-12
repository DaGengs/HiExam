package cn.iruier.service;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.Classes;

import java.util.List;

public interface ClassesService {
    ResultVo save(Classes classes);

    ResultVo update(String class_name, int class_id);

    ResultVo delete(int class_id);

    PageVo<Classes> queryByPage(int page, int size);

    List<Classes> queryAll();

}
