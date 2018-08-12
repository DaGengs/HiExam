package cn.iruier.service;

import cn.iruier.common.vo.MenuVo;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /*新增用户*/
    ResultVo save(User user);

    /*登录*/
    User login(String user_no, String user_password);

    ResultVo updatePwd(String new_password, String user_no);

    /*所有用户*/
    PageVo<User> queryByPage(int page, int size);

    /*删除用户*/
    ResultVo deleteUser(int user_id);

    /*更新对应角色*/
    ResultVo updateRole(int user_id, int[] role_ids);

    List<MenuVo> queryMenu(String user_no);
}
