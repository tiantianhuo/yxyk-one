package com.yxyk.reportory;

import com.yxyk.bean.po.Navigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by cw
 * Date: 2020/6/16
 * Time：11.12
 */
@Repository
public interface NavigationRepository extends JpaRepository<Navigation, Long> {

    List<Navigation> findByDeleteState(int code);


    Navigation findByIdAndDeleteState(Long id, int code);
}
