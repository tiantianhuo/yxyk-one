package com.yxyk.controller;

import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Role;
import com.yxyk.bean.po.User;
import com.yxyk.bean.vo.VoRole;
import com.yxyk.bean.vo.VoRoleAll;
import com.yxyk.service.RoleService;
import com.yxyk.service.UserService;
import com.yxyk.utils.VoChangeUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cw
 * @Description:
 * @Date: create in 2020/7/6 23:00
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/apis/role/")
public class RoleController extends BaseController {

    private final RoleService roleService;

    private final UserService userService;

    /**
     * 角色查询:全部查询+混合查询
     *
     * @param voRole 入参
     * @return JSONResponse
     */
    @PostMapping("findAllRoleName")
    public JSONResponse findRoleNameTime(@RequestBody VoRoleAll voRole) {
        Page<Role> rolePage = roleService.findByRoleNameAndStartTime(voRole);
        return this.success(rolePage);
    }

    /**
     * 查询单个角色
     *
     * @param id 角色id
     * @return JSONResponse
     */
    @PostMapping("findRoleById")
    public JSONResponse findRoleById(@RequestParam("id") Long id) {
        Role role = roleService.findByIdAndDeleteState(id);
        return this.success(role);
    }


    /**
     * 根据id删除角色
     *
     * @param id 主键id
     * @return JSONResponse
     */
    @PostMapping(value = "deleteRoleById")
    public JSONResponse deleteRoleById(@RequestParam("id") Long id) {
        List<User> userList = userService.findByRoleId(id);
        if (userList.size() == 0) {
            try {
                Role role = roleService.findByIdAndDeleteState(id);
                role.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
                roleService.saveRole(role);
            } catch (Exception e) {
                return this.error("删除出错");
            }
        } else {
            return this.error("此权限与其他用户关联,请先删除用户");
        }
        return this.success();
    }

    /**
     * 添加/修改角色
     *
     * @param voRole 角色vo
     * @return JSONResponse
     */
    @PostMapping(value = "addRole")
    public JSONResponse addRole(@RequestBody VoRole voRole) {
        roleService.saveRole(VoChangeUtils.changeToRole(voRole));
        return this.success();
    }

    /**
     * 查询所有角色
     * @return JSONResponse
     */
    @PostMapping("findAllRole")
    public JSONResponse findAllRole() {
        return this.success(roleService.findByDeleteState(SysConst.DeletedState.UN_DELETE_STATE.getCode()));
    }

}
