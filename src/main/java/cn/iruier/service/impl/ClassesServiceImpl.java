package cn.iruier.service.impl;

import cn.iruier.common.utils.StringUtils;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.dao.ClassesMapper;
import cn.iruier.entity.Classes;
import cn.iruier.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper mapper;

    @Override
    public ResultVo save(Classes classes) {
        if (mapper.save(classes) > 0) {
            return ResultVo.setSuccess("新增成功");
        } else {
            return ResultVo.setError("新增失败");
        }
    }

    @Override
    public ResultVo update(String class_name, int class_id) {
        if (StringUtils.empty(class_name)) {
            if (mapper.update(class_name, class_id) > 0) {
                return ResultVo.setSuccess("修改成功");
            } else {
                return ResultVo.setError("修改失败");
            }
        }
        return ResultVo.setError("修改失败");
    }

    @Override
    public ResultVo delete(int class_id) {
        if (class_id != 0) {
            if (mapper.delete(class_id) > 0) {
                return ResultVo.setSuccess("删除成功");
            } else {
                return ResultVo.setError("删除失败");
            }
        }
        return ResultVo.setError("删除失败");
    }

    @Override
    public PageVo<Classes> queryByPage(int page, int size) {
        PageVo<Classes> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<Classes> classes = mapper.queryByPage(index, size);
        if (classes != null) {
            pageVo.setCode(0);
            pageVo.setMsg("");
            pageVo.setCount(mapper.queryCount());
            pageVo.setData(classes);
        } else {
            pageVo.setData(new ArrayList<>());
            pageVo.setCount(0);
            pageVo.setMsg("暂无数据");
            pageVo.setCode(1);
        }
        return pageVo;
    }

    @Override
    public List<Classes> queryAll() {
        return mapper.queryAll();
    }
}
