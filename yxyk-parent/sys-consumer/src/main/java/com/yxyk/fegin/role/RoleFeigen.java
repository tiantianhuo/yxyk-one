package com.yxyk.fegin.role;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.vo.VoRole;
import com.yxyk.bean.vo.VoRoleAll;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: cw
 * @Description:
 * @Date: create in 2020/7/7 22:19
 */
@FeignClient(value = "menu")
public interface RoleFeigen {

    /**
     * 角色查询:全部查询+混合查询
     *
     * @param voRole 入参
     * @return JSONResponse
     */
    @PostMapping("/apis/role/findAllRoleName")
    JSONResponse findRoleNameTime(@RequestBody VoRoleAll voRole);

    /**
     * 查询单个角色
     *
     * @param id 角色id
     * @return JSONResponse
     */
    @PostMapping("/apis/role/findRoleById")
    JSONResponse findRoleById(@RequestParam("id") Long id);


    /**
     * 根据id删除角色
     *
     * @param id 主键id
     * @return JSONResponse
     */
    @PostMapping("/apis/role/deleteRoleById")
    JSONResponse deleteRoleById(@RequestParam("id") Long id);

    /**
     * 添加/修改角色
     *
     * @param voRole 角色vo
     * @return JSONResponse
     */
    @PostMapping("/apis/role/addRole")
    JSONResponse addRole(@RequestBody VoRole voRole);

    /**
     * 查询所有角色
     *
     * @return JSONResponse
     */
    @PostMapping("/apis/role/findAllRole")
    JSONResponse findAllRole();
}
