package org.softuni.accounting.areas.blog.repositories;

import org.softuni.accounting.areas.blog.domain.entities.Article;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleHomeViewModel;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> findTop3ByImagePathNotNullOrderByDateDesc();

    List<Article> findArticlesByAuthor(User author);

    List<Article> findArticlesByTitleContainingOrderByTitle(String title);

    List<Article> findTop6ByOrderByPageViewDesc();

    List<Article> findTop6ByOrderByDateDesc();

}
