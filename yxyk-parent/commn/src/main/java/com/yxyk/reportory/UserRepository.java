package com.yxyk.reportory;

import com.yxyk.bean.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:25
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    List<User> findByRoleIdAndDeleteState(Long id, int deleteState);

    Page<User> findAll(Specification<User> spec, Pageable pageable);

    User findByIdAndDeleteState(Long id, int deleteState);

    Optional<User> findByUserNameAndDeleteState(String userName, int deleteState);

    List<User> findAllByProcuratorIdAndDeleteStateAndAdminState(Long procuratorId,Integer deleteState,Integer adminState);
}
