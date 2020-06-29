package com.yxyk.reportory;

import com.yxyk.bean.po.AdminArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ArticleRepository extends JpaRepository<AdminArticle, Long>, JpaSpecificationExecutor<AdminArticle> {
    AdminArticle findByIdAndDeleteState(Long id, int code);
    AdminArticle findBySort(Long  sort);
    @Query(value=" SELECT c.id,c.sort  from admin_article c WHERE c.sort &lt; #{sort} ORDER BY c.sort DESC limit 0,1",nativeQuery = true)
    AdminArticle ArticleBySortUp(Long  sort);
    @Query(value="SELECT b.id,b.sort  from admin_article b WHERE b.sort &gt; #{sort} ORDER BY b.sort limit 0,1",nativeQuery = true)
    AdminArticle ArticleBySortDown(Long  sort);
    @Query(value=" UPDATE admin_article SET sort=#{sort} WHERE id=#{id}",nativeQuery = true)
    AdminArticle updateBySort(Long  sort,Long  id);
}
