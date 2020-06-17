package com.yxyk.repository;

import com.yxyk.bean.po.Article;
import com.yxyk.bean.po.Sensitive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository  extends JpaRepository<Article, Long> {
    Article findByIdAndStates(Long id, int code);
}
