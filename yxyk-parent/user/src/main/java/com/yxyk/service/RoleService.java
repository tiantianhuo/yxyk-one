package com.yxyk.service;

import com.yxyk.bean.po.Role;
import com.yxyk.bean.vo.VoRoleAll;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:48
 */
public interface RoleService extends BaseService<Role, Long> {

    Page<Role> findByRoleNameAndStartTime(VoRoleAll obj, long procuratorateId);

    Role findByIdAndDeleteState(Long id);

    Role saveRole(Role role);

    boolean savePermission(Long id, String permission);

    List<Role> findByProcuratorIdAndDeleteState(Long procuratorId, int deleteState);
}
