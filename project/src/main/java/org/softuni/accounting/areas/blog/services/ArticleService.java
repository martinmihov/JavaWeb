package org.softuni.accounting.areas.blog.services;

import org.softuni.accounting.areas.blog.domain.entities.Article;
import org.softuni.accounting.areas.blog.domain.models.binding.ArticleBindingModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleDeleteEditViewModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleHomeViewModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleViewModel;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    void editArticle(Long id,ArticleBindingModel model,MultipartFile image);

    void deleteArticle(Long id);

    void savePost(ArticleBindingModel model,MultipartFile image);

    boolean isUserAuthorOrAdmin(Article article);

    String incrementView(Long article_id);

    ArticleViewModel viewArticle(Long id);

    ArticleDeleteEditViewModel getArticleToEditOrDelete(Long id);

    List<ArticleViewModel> getSixMostPopularArticles();

    List<ArticleViewModel> getSixMostRecentArticles();

    Page<ArticleViewModel> findAllByPage(Pageable pageable);

    List<ArticleViewModel> searchArticlesByNameContaining(String title);

    List<ArticleHomeViewModel> getArticlesIndexPage();

    List<ArticleHomeViewModel> getArticlesByAuthor(ProfileViewModel author);

}
