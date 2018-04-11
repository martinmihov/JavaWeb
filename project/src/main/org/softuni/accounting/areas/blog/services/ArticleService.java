package org.softuni.accounting.areas.blog.services;

import org.softuni.accounting.areas.blog.domain.entities.Article;
import org.softuni.accounting.areas.blog.domain.models.binding.ArticleBindingModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleDeleteEditViewModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    void savePost(ArticleBindingModel model,MultipartFile image);

    ArticleDeleteEditViewModel getArticleToDelete(Long id);

    ArticleViewModel viewArticle(Long id);

    ArticleBindingModel findById(Long id);

    void editArticle(Long id,ArticleBindingModel model);

    void deleteArticle(Long id);

    boolean isUserAuthorOrAdmin(Article article);

    String incrementView(Long article_id);

    List<ArticleViewModel> getArticlesMainPage();
}
