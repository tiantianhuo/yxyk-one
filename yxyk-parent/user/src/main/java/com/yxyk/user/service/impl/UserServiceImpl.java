package com.yxyk.user.service.impl;//package com.com.yxyk.user.service.impl;
//
//import com.com.yxyk.user.bean.common.OperationException;
//import com.com.yxyk.user.bean.common.SysConst;
//import com.com.yxyk.user.bean.po.Role;
//import com.com.yxyk.user.bean.po.User;
//import com.com.yxyk.user.reportory.RoleRepository;
//import com.com.yxyk.user.utils.DynamicSpecifications;
//import com.com.yxyk.user.utils.SearchFilter;
//import com.com.yxyk.user.reportory.UserRepository;
//import com.com.yxyk.user.service.UserService;
//import lombok.AllArgsConstructor;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
///**
// * created with IntelliJ IDEA
// *
// * @author: create by limu
// * Date: 2019/10/10
// * Time：14:24
// */
//@Service
//@AllArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//
//    @Override
//    public void saveUser(User user, User loginUser) throws OperationException {
//        Long userId = user.getId();
//        if (userId != null && userId != 0L) {
//            User dbUser = this.findById(userId).orElseThrow(() -> new OperationException("用户不存在或者已经被删除"));
//            if (Objects.equals(dbUser.getId(), userId)) {
//                if (!("".equals(user.getPassword()))) {
//                    int salt = new Random().nextInt(5000);
//                    dbUser.setSalt(Integer.toString(salt));
//                    dbUser.setPassword(new SimpleHash(SysConst.SHIRO_PASSWORD_TYPE, user.getPassword(), Integer.toString(salt), SysConst.SHIRO_PASSWORD_COUNT).toString());
//                }
//                dbUser.setUserName(user.getUserName());
//                dbUser.setRoleId(user.getRoleId());
//                dbUser.setRemarks(user.getRemarks());
//                dbUser.setUpdateTime(LocalDateTime.now());
//
//                userRepository.save(dbUser);
//            } else {
//                throw new OperationException("用户名称重复,换一个试试吧");
//            }
//        } else {
//            Optional<User> userOptional = userRepository.findByUserNameAndDeleteState(user.getUserName(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
//            if (!userOptional.isPresent()) {
//                int salt = new Random().nextInt(5000);
//                user.setSalt(Integer.toString(salt));
//                user.setParentId(loginUser.getParentId());
//                user.setAdminState(SysConst.IsAdmin.UN_ADMIN.getCode());
//                user.setProcuratorId(loginUser.getProcuratorId());
//                user.setStartTime(loginUser.getStartTime());
//                user.setEndTime(loginUser.getEndTime());
//                user.setPassword(new SimpleHash(SysConst.SHIRO_PASSWORD_TYPE, user.getPassword(), Integer.toString(salt), SysConst.SHIRO_PASSWORD_COUNT).toString());
//                user.setCreateTime(LocalDateTime.now());
//                userRepository.save(user);
//            } else {
//                throw new OperationException("用户名称重复,换一个试试吧");
//            }
//
//        }
//    }
//
//    @Override
//    public List<User> findByRoleId(Long id) {
//        return userRepository.findByRoleIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
//    }
//
//    @Override
//    public Page<User> findAll(LocalDateTime startTime, LocalDateTime endTime, String username, Long roleId, Integer pageNum, Integer pageSize, Long procuratorId) {
//        Map<String, SearchFilter> map = new HashMap<>();
//        map.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
//        // 结束时间
//        if (endTime != null) {
//            map.put("endTime", new SearchFilter("updateTime", SearchFilter.Operator.LTE, endTime));
//        }
//        // 开始时间
//        if (startTime != null) {
//            map.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, startTime));
//        }
//        // 角色
//        if (roleId != null && 0L != roleId) {
//            map.put("departmentId", new SearchFilter("roleId", SearchFilter.Operator.EQ, roleId));
//        }
//        // 用户名
//        if (StringUtils.isNotEmpty(username)) {
//            map.put("username", new SearchFilter("userName", SearchFilter.Operator.LIKE, username));
//        }
//        map.put("parentId", new SearchFilter("parentId", SearchFilter.Operator.NEQ, 0));
//        map.put("procuratorId", new SearchFilter("procuratorId", SearchFilter.Operator.EQ, procuratorId));
//        map.put("adminState", new SearchFilter("adminState", SearchFilter.Operator.EQ, SysConst.IsAdmin.UN_ADMIN.getCode()));
//
//        Specification<User> specification = DynamicSpecifications.bySearchFilter(map.values(), User.class);
//        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
//        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
//
//        Page<User> page = userRepository.findAll(specification, pageRequest);
//
//        if (page.getContent().size() != 0) {
//            for (User user : page) {
//                Role role = roleRepository.findByIdAndDeleteState(user.getRoleId(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
//                if(role!=null){
//                    user.setRoleName(role.getRoleName());
//                }else {
//                    user.setRoleName("");
//                }
//            }
//        }
//        return page;
//    }
//
//    @Override
//    public User findByIdAndDeleteState(Long id) {
//        return userRepository.findByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
//    }
//
//    @Override
//    public boolean delUser(User user) {
//        user.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
//        userRepository.save(user);
//        return true;
//    }
//
//    @Override
//    public void updatePassword(Long userId, String password, String newPassword, String homeUrl) throws OperationException {
//        User user = userRepository.findByIdAndDeleteState(userId, SysConst.DeletedState.UN_DELETE_STATE.getCode());
//        String orgPassword = new SimpleHash(SysConst.SHIRO_PASSWORD_TYPE, password, user.getSalt(), SysConst.SHIRO_PASSWORD_COUNT).toString();
//        if ("".equals(password) && "".equals(newPassword)) {
//            user.setHomeUrl(homeUrl);
//            user.setUpdateTime(LocalDateTime.now());
//            userRepository.save(user);
//        } else {
//            if (orgPassword.equals(user.getPassword())) {
//                int salt = new Random().nextInt(5000);
//                user.setSalt(Integer.toString(salt));
//                user.setPassword(new SimpleHash(SysConst.SHIRO_PASSWORD_TYPE, newPassword, Integer.toString(salt), SysConst.SHIRO_PASSWORD_COUNT).toString());
//                user.setUpdateTime(LocalDateTime.now());
//                user.setHomeUrl(homeUrl);
//                userRepository.save(user);
//            } else {
//                throw new OperationException("原密码错误!");
//            }
//        }
//
//
//    }
//
//    @Override
//    public List<User> findByProcuratorIdAndDeleteState(Long item, int code) {
//        return findByProcuratorIdAndDeleteState(item,code);
//    }
//
//    @Override
//    public List<User> findAllByProcuratorIdAndAdminState(Long procuratorId, Integer adminState) {
//        return userRepository.findAllByProcuratorIdAndDeleteStateAndAdminState(procuratorId, SysConst.DeletedState.UN_DELETE_STATE.getCode(), adminState);
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        return userRepository.findById(id);
//    }
//}
