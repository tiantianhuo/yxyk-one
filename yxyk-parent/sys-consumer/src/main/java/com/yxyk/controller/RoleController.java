package com.yxyk.controller;

import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Role;
import com.yxyk.bean.po.User;
import com.yxyk.bean.vo.VoRole;
import com.yxyk.bean.vo.VoRoleAll;
import com.yxyk.fegin.role.RoleFeigen;
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

    private final RoleFeigen roleFeigen;


    /**
     * 角色查询:全部查询+混合查询
     *
     * @param voRole 入参
     * @return JSONResponse
     */
    @PostMapping("findAllRoleName")
    public JSONResponse findRoleNameTime(@RequestBody VoRoleAll voRole) {
        return roleFeigen.findRoleNameTime(voRole);

    }

    /**
     * 查询单个角色
     *
     * @param id 角色id
     * @return JSONResponse
     */
    @PostMapping("findRoleById")
    public JSONResponse findRoleById(@RequestParam("id") Long id) {
        return roleFeigen.findRoleById(id);
    }


    /**
     * 根据id删除角色
     *
     * @param id 主键id
     * @return JSONResponse
     */
    @PostMapping(value = "deleteRoleById")
    public JSONResponse deleteRoleById(@RequestParam("id") Long id) {
        return roleFeigen.deleteRoleById(id);
    }

    /**
     * 添加/修改角色
     *
     * @param voRole 角色vo
     * @return JSONResponse
     */
    @PostMapping(value = "addRole")
    public JSONResponse addRole(VoRole voRole) {
        return roleFeigen.addRole(voRole);
    }

    /**
     * 查询所有角色
     *
     * @return JSONResponse
     */
    @PostMapping("findAllRole")
    public JSONResponse findAllRole() {
        return roleFeigen.findAllRole();
    }

}
