package com.yxyk.repository;

import com.yxyk.bean.po.AdminSensitive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * created with IntelliJ IDEA
 *
 * @author: create by zhaokai
 * Date: 2020/6/16
 * Time：14:25
 */
@Repository
public interface SensitiveRepository extends JpaRepository<AdminSensitive, Long> {
    AdminSensitive findByIdAndDeleteState(Long id, int code);
    Page<AdminSensitive> findAll(Specification<AdminSensitive> spec, Pageable pageable);
}
