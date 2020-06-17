package com.yxyk.reportory;

import com.yxyk.bean.po.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * Created with IntelliJ IDEA.
 *
 * @author zhanghao
 * date: 2019/10/10 16:43
 * description:
 */
@Repository
public interface BannerSysRepository extends JpaRepository<Banner, Long>, JpaSpecificationExecutor<Banner>, PagingAndSortingRepository<Banner, Long> {

    Banner findAllByIdAndDeleteState(Long id, int i);

}
