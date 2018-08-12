package cn.iruier.dao;

import cn.iruier.common.vo.TestListVo;
import cn.iruier.entity.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapper {
    int save(Test test);

    List<TestListVo> queryByUser_no(@Param("user_no") String user_no, @Param("index")int index, @Param("size")int size);

    int queryCountByNo(@Param("user_no") String user_no);

    int updateStatus(int test_id);
}
