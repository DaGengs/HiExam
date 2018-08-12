package cn.iruier.dao;

import cn.iruier.entity.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperMapper {
    int save(Paper paper);

    List<Paper> queryByPage(@Param("index")int index, @Param("size")int size);

    int queryCount();

    int queryCountByStatus();

    List<Paper> queryByStatus(@Param("index")int index, @Param("size")int size);

    Paper queryByPaperId(int paper_id);
}
