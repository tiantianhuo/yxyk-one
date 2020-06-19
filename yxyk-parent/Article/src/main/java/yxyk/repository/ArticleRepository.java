package yxyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yxyk.bean.po.AdminArticle;


@Repository
public interface ArticleRepository  extends JpaRepository<AdminArticle, Long> {
    AdminArticle findByIdAndDeleteState(Long id, int code);

}
