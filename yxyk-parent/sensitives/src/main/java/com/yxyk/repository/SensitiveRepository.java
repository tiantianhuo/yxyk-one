package com.yxyk.repository;

import com.yxyk.bean.po.Sensitive;
import com.yxyk.bean.po.User;
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
public interface SensitiveRepository extends JpaRepository<Sensitive, Long> {
    Sensitive findByIdAndStates(Long id, int code);
    Page<Sensitive> findAll(Specification<Sensitive> spec, Pageable pageable);
}
