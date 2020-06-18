package com.yxyk.user.service;//package com.com.yxyk.user.service;
//
//import com.com.yxyk.user.bean.po.Role;
//import com.com.yxyk.user.bean.vo.VoRoleAll;
//import com.com.yxyk.user.service.common.BaseService;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//
///**
// * created with IntelliJ IDEA
// *
// * @author: create by limu
// * Date: 2019/10/10
// * Time：14:48
// */
//public interface RoleService extends BaseService<Role, Long> {
//
//    Page<Role> findByRoleNameAndStartTime(VoRoleAll obj, long procuratorateId);
//
//    Role findByIdAndDeleteState(Long id);
//
//    Role saveRole(Role role);
//
//    boolean savePermission(Long id, String permission);
//
//    List<Role> findByProcuratorIdAndDeleteState(Long procuratorId, int deleteState);
//}
