package com.yxyk.service;


import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.User;
import com.yxyk.bean.vo.VoUserSearch;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:23
 */
public interface UserService extends BaseService<User, Long> {

    /**
     * 保存或者修改用户
     *
     * @param user 用户对象
     * @throws OperationException 自定义对象
     */
    void saveUser(User user) throws OperationException;

    Page<User> findUserPage(VoUserSearch voUserSearch);

    void deleteUser(Long id) throws OperationException;

    Optional<User> findById(Long id);
}
