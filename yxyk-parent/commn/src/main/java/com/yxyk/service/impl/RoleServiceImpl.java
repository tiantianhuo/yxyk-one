package com.yxyk.service.impl;

import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Role;
import com.yxyk.bean.po.User;
import com.yxyk.bean.vo.VoRoleAll;
import com.yxyk.reportory.RoleRepository;
import com.yxyk.reportory.UserRepository;
import com.yxyk.service.RoleService;
import com.yxyk.service.UserService;
import com.yxyk.utils.DateUtils;
import com.yxyk.utils.DynamicSpecifications;
import com.yxyk.utils.SearchFilter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:48
 */
@AllArgsConstructor
@Service
@Transactional(rollbackOn = RuntimeException.class)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Override
    public Page<Role> findByRoleNameAndStartTime(VoRoleAll voRole) {

        String roleName = voRole.getRoleName();
        int curr = voRole.getPageNum();
        int pageSize = voRole.getPageSize();
        Map<Object, SearchFilter> filters = new HashMap<Object, SearchFilter>();

        filters.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
      //  filters.put("procuratorId", new SearchFilter("procuratorId", SearchFilter.Operator.EQ, procuratorId));

        if (roleName != null && roleName != "") {
            filters.put("roleName", new SearchFilter("roleName", SearchFilter.Operator.LIKE, roleName));
        }

        if (StringUtils.isNotBlank(voRole.getStartTime())) {
            filters.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, DateUtils.parseDateTime(voRole.getStartTime())));
        }
        if (StringUtils.isNotBlank(voRole.getEndTime())) {
            filters.put("endTime", new SearchFilter("createTime", SearchFilter.Operator.LTE, DateUtils.parseDateTime(voRole.getEndTime())));
        }

        Specification<Role> specification = DynamicSpecifications.bySearchFilter(filters.values(), Role.class);

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(curr, pageSize, sort);

        return roleRepository.findAll(specification, pageRequest);
    }

    @Override
    public Role findByIdAndDeleteState(Long id) {
        return roleRepository.findByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }

    @Override
    public Role saveRole(Role role) {
        List<User> userList = userRepository.findByRoleIdAndDeleteState(role.getId(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
        for (User user : userList) {
            user.setPressStr(role.getPermissions());
            userRepository.save(user);
        }
        return roleRepository.save(role);
    }



    @Override
    public List<Role> findByDeleteState(int deleteState) {
        return roleRepository.findByDeleteState(SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }

}
