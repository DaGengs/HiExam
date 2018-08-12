package cn.iruier.web.controller;

import cn.iruier.common.utils.MD5Utils;
import cn.iruier.common.vo.MenuVo;
import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.entity.User;
import cn.iruier.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/saveUser.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo saveUser(@RequestBody User user) {
        return service.save(user);
    }

    @RequestMapping(value = "/login.do", method = {RequestMethod.POST})
    public String login(User user, int role_id, HttpServletRequest request) {
        User user1 = service.login(user.getUser_no(), user.getUser_password());

        if (user1 != null) {
            if (role_id != user1.getRoles().get(0).getRole_id()) {
                return "redirect:error.html";
            }
            request.getSession().setAttribute("user", user1);
            return "redirect:index.html";
        } else {
            return "redirect:login.html";
        }
    }

    @RequestMapping("/userQueryByPage.do")
    @ResponseBody
    public PageVo<User> userqueryByPage(int page, int limit) {
        return service.queryByPage(page, limit);
    }

    @RequestMapping("/deleteUser.do")
    @ResponseBody
    public ResultVo deleteUser(int user_id) {
        return service.deleteUser(user_id);
    }

    @RequestMapping("/loginOut.do")
    public String loginOut() {
        SecurityUtils.getSubject().logout();
        return "redirect: login.html";
    }

    @RequestMapping(value = "/updateMyRole.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo updateMyRole(int user_id, @RequestParam("role_ids[]") int[] role_ids) {
        return service.updateRole(user_id, role_ids);
    }

    @RequestMapping("/getUser.do")
    @ResponseBody
    public User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    @RequestMapping("/getMenu.do")
    @ResponseBody
    public List<MenuVo> getMenu() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return service.queryMenu(user.getUser_no());
    }

    @RequestMapping(value = "/getPassword.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo getPassword(String password) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if (Objects.equals(MD5Utils.md5(password), user.getUser_password())) {
            return ResultVo.setSuccess("");
        } else {
            return ResultVo.setError("输入的旧密码不正确");
        }
    }

    @RequestMapping(value = "edidPassword.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo edidPassword(String new_password) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return service.updatePwd(new_password, user.getUser_no());
    }
}
