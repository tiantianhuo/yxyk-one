package com.yxyk.user.controller;//package com.com.yxyk.user.controller;
//
//
//import com.com.yxyk.user.bean.common.JSONResponse;
//import com.com.yxyk.user.bean.common.SysConst;
//import com.com.yxyk.user.bean.po.Role;
//import com.com.yxyk.user.bean.po.User;
//import com.com.yxyk.user.bean.vo.VoRoleAll;
//import com.com.yxyk.user.controller.common.BaseController;
//import com.com.yxyk.user.service.RoleService;
//import com.com.yxyk.user.service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//
///**
// * created with IntelliJ IDEA
// *
// * @author: create by ay
// */
//@RestController
//@RequestMapping(value = "/apis/role/")
//@AllArgsConstructor
//public class RoleController extends BaseController {
//
//    private final RoleService roleService;
//
//    private final UserService userService;
//
//    /**
//     * 角色查询:全部查询+混合查询
//     *
//     * @param voRole 入参
//     * @return JSONResponse
//     */
//    @PostMapping("findAllRoleName")
//    public JSONResponse findRoleNameTime(VoRoleAll voRole) {
//        long procuratorId = this.getUser().getProcuratorId();
//        Page<Role> rolePage = roleService.findByRoleNameAndStartTime(voRole, procuratorId);
//        return this.success(rolePage);
//    }
//
//    /**
//     * 查询单个角色
//     *
//     * @param id 角色id
//     * @return JSONResponse
//     */
//    @PostMapping("findRoleById")
//    public JSONResponse findRoleById(Long id) {
//        Role role = roleService.findByIdAndDeleteState(id);
//        return this.success(role);
//    }
//
//    /**
//     * 根据id修改角色
//     *
//     * @return Boolean
//     */
//    @PostMapping(value = "updateRole")
//    public Boolean updateRole(Long id, String roleName, String remarks) {
//        Role role = roleService.findByIdAndDeleteState(id);
//        role.setRoleName(roleName);
//        role.setRemarks(remarks);
//        Role newRole = roleService.saveRole(role);
//        if (newRole != null) {
//            return Boolean.TRUE;
//        } else {
//            return Boolean.FALSE;
//        }
//    }
//
//    /**
//     * 根据id删除角色
//     *
//     * @param id 主键id
//     * @return JSONResponse
//     */
//    @PostMapping(value = "deleteRoleById")
//    public JSONResponse deleteRoleById(Long id) {
//        List<User> userList = userService.findByRoleId(id);
//        if (userList.size() == 0) {
//            try {
//                Role role = roleService.findByIdAndDeleteState(id);
//                role.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
//                roleService.saveRole(role);
//            } catch (Exception e) {
//                return this.error("删除出错");
//            }
//        } else {
//            return this.error("此权限与其他用户关联,请先删除用户");
//        }
//        return this.success();
//    }
//
//    /**
//     * 添加角色
//     *
//     * @param roleName 角色名称
//     * @param remarks  角色备注
//     * @return JSONResponse
//     */
//    @PostMapping(value = "addRole")
//    public JSONResponse addRole(String roleName, String remarks) {
//        Role role = new Role();
//        role.setRoleName(roleName);
//        role.setRemarks(remarks);
//        role.setProcuratorId(this.getUser().getProcuratorId());
//        role.setCreateTime(LocalDateTime.now());
//        role.setUpdateTime(LocalDateTime.now());
//        roleService.saveRole(role);
//        return this.success();
//    }
//
//    /**
//     * 修改权限
//     *
//     * @param id         当前角色id
//     * @param permission 权限
//     * @return JSONResponse
//     */
//    @PostMapping("savePermission")
//    public JSONResponse savePermission(Long id, String permission) {
//        try {
//            roleService.savePermission(id, permission);
//            return this.success();
//        } catch (Exception e) {
//            return this.error("修改权限出错");
//        }
//    }
//
//    /**
//     * 获取当前检察院下所有的角色
//     *
//     * @return JSONResponse
//     */
//    @PostMapping("findRoleAll")
//    public JSONResponse findRoleAll() {
//        Long procuratorId = this.getUser().getProcuratorId();
//        List<Role> list = roleService.findByProcuratorIdAndDeleteState(procuratorId,SysConst.DeletedState.UN_DELETE_STATE.getCode());
//        return this.success(list);
//    }
//}
