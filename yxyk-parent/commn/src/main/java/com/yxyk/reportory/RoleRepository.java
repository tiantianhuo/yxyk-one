package com.yxyk.reportory;

import com.yxyk.bean.po.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:47
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findAll(Specification<Role> spec, Pageable pageable);

    Role findByIdAndDeleteState(Long id, int deleteState);

    List<Role> findByProcuratorIdAndDeleteState(Long procuratorId, int deleteState);
}
