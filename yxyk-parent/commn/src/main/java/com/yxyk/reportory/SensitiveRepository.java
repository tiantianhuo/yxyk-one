package com.yxyk.reportory;

import com.yxyk.bean.po.AdminSensitive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * created with IntelliJ IDEA
 *
 * @author: create by zhaokai
 * Date: 2020/6/16
 * Time：14:25
 */
@Repository
public interface SensitiveRepository extends JpaRepository<AdminSensitive, Long>,JpaSpecificationExecutor<AdminSensitive>{
    AdminSensitive findByIdAndDeleteState(Long id, int code);
}
