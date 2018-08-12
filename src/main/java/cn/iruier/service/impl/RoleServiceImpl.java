package cn.iruier.service.impl;

import cn.iruier.common.vo.PageVo;
import cn.iruier.common.vo.ResultVo;
import cn.iruier.dao.RoleMapper;
import cn.iruier.entity.Role;
import cn.iruier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public ResultVo save(String user_name) {
        if (mapper.save(user_name) > 0) {
            return ResultVo.setSuccess("新增成功");
        } else {
            return ResultVo.setError("新增失败");
        }
    }

    @Override
    public ResultVo deleteRole(int role_id) {
        if (mapper.deleteRole(role_id) > 0) {
            return ResultVo.setSuccess("删除成功");
        } else {
            return ResultVo.setError("删除失败");
        }
    }

    @Override
    public PageVo<Role> queryByPage(int page, int size) {
        PageVo<Role> pageVo = new PageVo<>();
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        List<Role> roles = mapper.queryByPage(index, size);
        if (roles != null) {
            pageVo.setCode(0);
            pageVo.setCount(mapper.queryCount());
            pageVo.setMsg("");
            pageVo.setData(roles);
        } else {
            pageVo.setCode(1);
            pageVo.setCount(0);
            pageVo.setMsg("暂无数据");
            pageVo.setData(new ArrayList<>());
        }
        return pageVo;
    }

    @Override
    public List<Role> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public ResultVo updateMenu(int role_id, int[] menu_ids) {
        if (mapper.deleteMenu(role_id) >= 0) {
            System.out.println("111");
            try {
                for (int i = 0; i < menu_ids.length; i++) {
                    mapper.updateMenu(role_id, menu_ids[i]);
                    System.out.println("222");
                }
                return ResultVo.setSuccess("更新权限成功");
            } catch (Exception e) {
                System.out.println("333");
                return ResultVo.setError("更新权限失败");
            }
        }
        System.out.println("444");
        return ResultVo.setError("更新权限失败");
    }
}
