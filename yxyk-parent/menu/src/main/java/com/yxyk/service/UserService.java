package com.yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.User;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:23
 */
public interface UserService extends BaseService<User, Long> {

    void saveUser(User user, User loginUser) throws OperationException;

    List<User> findByRoleId(Long id);

    Page<User> findAll(LocalDateTime startTime, LocalDateTime endTime, String username, Long departmentId, Integer pageNum, Integer pageSize, Long procuratorId);

    User findByIdAndDeleteState(Long id);

    boolean delUser(User user);

    void updatePassword(Long userId, String password, String newPassword, String homeUrl) throws OperationException;

    List<User> findByProcuratorIdAndDeleteState(Long item, int code);

    List<User> findAllByProcuratorIdAndAdminState(Long procuratorId,Integer adminState);
}
