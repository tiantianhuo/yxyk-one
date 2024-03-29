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

    Page<Role> findByRoleNameAndStartTime(VoRoleAll obj);

    Role findByIdAndDeleteState(Long id);

    Role saveRole(Role role);

    List<Role> findByDeleteState(int deleteState);
}
