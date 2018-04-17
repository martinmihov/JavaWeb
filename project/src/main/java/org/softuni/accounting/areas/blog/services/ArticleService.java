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

    List<ArticleViewModel> searchArticlesByNameContaining(String title);

    void savePost(ArticleBindingModel model,MultipartFile image);

    ArticleDeleteEditViewModel getArticleToEditOrDelete(Long id);

    ArticleViewModel viewArticle(Long id);

    ArticleBindingModel findById(Long id);

    void editArticle(Long id,ArticleBindingModel model);

    void deleteArticle(Long id);

    boolean isUserAuthorOrAdmin(Article article);

    String incrementView(Long article_id);

    List<ArticleViewModel> getArticlesBlogMainPage();

    List<ArticleHomeViewModel> getArticlesIndexPage();

    List<ArticleHomeViewModel> getArticlesByAuthor(ProfileViewModel author);

    Page<ArticleViewModel> findAllByPage(Pageable pageable);

    long getTotalPages(int size);

    default long getTotalPages(){
        return getTotalPages(6);
    }
}