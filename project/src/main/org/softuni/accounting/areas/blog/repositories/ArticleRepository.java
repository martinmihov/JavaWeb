package org.softuni.accounting.areas.blog.repositories;

import org.softuni.accounting.areas.blog.domain.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
}
