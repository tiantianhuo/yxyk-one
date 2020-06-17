package com.yxyk.user.service.impl;//package com.com.yxyk.user.service.impl;
//
//import com.com.yxyk.user.bean.common.SysConst;
//import com.com.yxyk.user.bean.po.Role;
//import com.com.yxyk.user.bean.vo.VoRoleAll;
//import com.com.yxyk.user.reportory.RoleRepository;
//import com.com.yxyk.user.service.RoleService;
//import com.com.yxyk.user.utils.DynamicSpecifications;
//import com.com.yxyk.user.utils.SearchFilter;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * created with IntelliJ IDEA
// *
// * @author: create by limu
// * Date: 2019/10/10
// * Time：14:48
// */
//@AllArgsConstructor
//@Service
//@Transactional(rollbackOn = RuntimeException.class)
//public class RoleServiceImpl implements RoleService {
//
//    private final RoleRepository roleRepository;
//
//    @Override
//    public Page<Role> findByRoleNameAndStartTime(VoRoleAll voRole, long procuratorId) {
//        LocalDateTime startTime = voRole.getStartTime();
//        LocalDateTime endTime = voRole.getEndTime();
//        String roleName = voRole.getRoleName();
//        int curr = voRole.getPageNum();
//        int pageSize = voRole.getPageSize();
//        Map<Object, SearchFilter> filters = new HashMap<Object, SearchFilter>();
//
//        filters.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
//        filters.put("procuratorId", new SearchFilter("procuratorId", SearchFilter.Operator.EQ, procuratorId));
//
//        if (roleName != null && roleName != "") {
//            filters.put("roleName", new SearchFilter("roleName", SearchFilter.Operator.LIKE, roleName));
//        }
//
//        if (startTime != null) {
//            filters.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, startTime));
//        }
//
//        if (endTime != null) {
//            filters.put("endTime", new SearchFilter("updateTime", SearchFilter.Operator.LTE, endTime));
//        }
//
//        Specification<Role> specification = DynamicSpecifications.bySearchFilter(filters.values(), Role.class);
//
//        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
//        PageRequest pageRequest = PageRequest.of(curr - 1, pageSize, sort);
//
//        return roleRepository.findAll(specification, pageRequest);
//    }
//
//    @Override
//    public Role findByIdAndDeleteState(Long id) {
//        return roleRepository.findByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
//    }
//
//    @Override
//    public Role saveRole(Role role) {
//        return roleRepository.save(role);
//    }
//
//    @Override
//    public boolean savePermission(Long id, String permission) {
//        Role role = roleRepository.findByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
//        role.setPermissions(permission);
//        roleRepository.save(role);
//        return true;
//    }
//
//    @Override
//    public List<Role> findByProcuratorIdAndDeleteState(Long procuratorId, int i) {
//        return roleRepository.findByProcuratorIdAndDeleteState(procuratorId, SysConst.DeletedState.UN_DELETE_STATE.getCode());
//    }
//
//}
