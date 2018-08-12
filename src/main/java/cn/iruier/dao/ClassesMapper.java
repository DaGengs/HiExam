package cn.iruier.dao;

import cn.iruier.entity.Classes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassesMapper {
    int save(Classes classes);

    int update(@Param("class_name") String class_name,@Param("class_id") int class_id);

    int delete(int class_id);

    List<Classes> queryByPage(@Param("index")int index, @Param("size")int size);

    List<Classes> queryAll();

    int queryCount();
}
