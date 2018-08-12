package cn.iruier.service.impl;

import cn.iruier.common.utils.MD5Utils;
import cn.iruier.common.utils.StringUtils;
import cn.iruier.common.vo.MenuVo;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.dao.UserMapper;
import cn.iruier.entity.User;
import cn.iruier.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public ResultVo save(User user) {
        if (mapper.save(user) > 0) {
            return ResultVo.setSuccess("新增成功");
        } else {
            return ResultVo.setError("新增失败");
        }
    }

    @Override
    public User login(String user_no, String user_password) {
        if (StringUtils.empty(user_no, user_password)) {
            User user = mapper.queryByNo(user_no);
            if (user != null) {
                if (Objects.equals(MD5Utils.md5(user_password), user.getUser_password())) {
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(user_no, MD5Utils.md5(user_password));
                    subject.login(token);
                    subject.getSession().setAttribute("user", user);
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public PageVo<User> queryByPage(int page, int size) {
        PageVo<User> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<User> users = mapper.queryByPage(index, size);
        if (users != null) {
            pageVo.setCode(0);
            pageVo.setCount(mapper.queryCount());
            pageVo.setMsg("");
            pageVo.setData(users);
        } else {
            pageVo.setCode(1);
            pageVo.setCount(0);
            pageVo.setMsg("暂无数据");
            pageVo.setData(new ArrayList<>());
        }
        return pageVo;
    }

    @Override
    public ResultVo deleteUser(int user_id) {
        if (user_id != 0) {
            if (mapper.deleteUser(user_id) > 0) {
                return ResultVo.setSuccess("删除成功");
            } else {
                return ResultVo.setError("删除失败");
            }
        }
        return ResultVo.setError("删除失败");
    }

    @Override
    public ResultVo updateRole(int user_id, int[] role_ids) {
        System.out.println(role_ids.length);
        if (mapper.deleteRole(user_id) >= 0) {
            try {
                for (int i = 0; i < role_ids.length; i++) {
                    mapper.updateRole(user_id, role_ids[i]);
                }
                return ResultVo.setSuccess("分配角色成功");
            } catch (Exception e) {
                return ResultVo.setError("分配角色失败");
            }
        } else {
            return ResultVo.setError("分配角色失败");
        }

    }

    @Override
    public List<MenuVo> queryMenu(String user_no) {
        List<MenuVo> menuVos = mapper.queryMenu(user_no);
        for (MenuVo menuVo : menuVos) {
            menuVo.setChildren(mapper.queryChildMenu(menuVo.getId()));
        }
        return menuVos;
    }

    @Override
    public ResultVo updatePwd(String new_password, String user_no) {
        if (StringUtils.empty(new_password, user_no)) {
            new_password = MD5Utils.md5(new_password);
            if (mapper.updatePwd(new_password, user_no) > 0) {
                return  ResultVo.setSuccess("修改成功,5s后退出重新登录");
            } else {
                return ResultVo.setError("修改失败");
            }

        }
        return ResultVo.setError("修改失败");
    }
}
