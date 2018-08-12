package cn.iruier.dao;

import cn.iruier.common.vo.MenuVo;
import cn.iruier.entity.Menu;
import cn.iruier.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /*新增用户*/
    int save(User user);

    /*登录*/
    User queryByNo(String user_no);

    int updatePwd(@Param("new_password") String new_password, @Param("user_no") String user_no);

    /*所有用户*/
    List<User> queryByPage(@Param("index") int index, @Param("size") int size);

    /*查询数量*/
    int queryCount();

    /*删除用户*/
    int deleteUser(int user_id);

    /*删除对应角色*/
    int deleteRole(int user_id);

    /*更新对应角色*/
    int updateRole(@Param("user_id") int user_id, @Param("role_id") int role_id);

    List<MenuVo> queryMenu(String user_no);

    List<Menu> queryChildMenu(@Param("menu_id") int menu_id);
}
