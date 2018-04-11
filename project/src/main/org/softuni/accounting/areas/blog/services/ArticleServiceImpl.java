package org.softuni.accounting.areas.blog.services;

import org.softuni.accounting.areas.blog.domain.entities.Article;
import org.softuni.accounting.areas.blog.domain.models.binding.ArticleBindingModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleDeleteEditViewModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleViewModel;
import org.softuni.accounting.areas.blog.repositories.ArticleRepository;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final ModelParser modelParser;

    private boolean isAuthor(Article article){
        return Objects.equals(loggedInUser().getId(),
                article.getAuthor().getId());
    }

    private User loggedInUser(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return this.userService.findByEmail(user.getUsername());
    }

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService, ModelParser modelParser) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.modelParser = modelParser;
    }

    @Override
    public ArticleViewModel viewArticle(Long id) {
        Optional<Article> optionalArticle = this.articleRepository.findById(id);
        if(optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            return this.modelParser.convert(article,ArticleViewModel.class);
        }
        return null;
    }

    @Override
    public void savePost(ArticleBindingModel model , MultipartFile image) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User userEntity = this.userService.findByEmail(user.getUsername());
        String imagePath = null;
        String[] allowedContentTypes = {
                "image/png",
                "image/jpg",
                "image/jpeg",
                "image/gif"
        };
        boolean isContentTypeAllowed = Arrays.asList(allowedContentTypes)
                .contains(model.getImage().getContentType());
        if (isContentTypeAllowed) {
            String imagesPath = "\\src\\resources\\static\\images\\";
            String imagePathRoot = System.getProperty("user.dir");
            String imageSaveDirectory = imagePathRoot + imagesPath;
            String filename = model.getImage().getOriginalFilename();
            String savePath = imageSaveDirectory + filename;
            File imageFile = new File(savePath);
            try {
                model.getImage().transferTo(imageFile);
                imagePath = "/images/" + filename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Article article = new Article(
                model.getTitle(),
                model.getContent(),
                userEntity,
                imagePath,
                model.getDate()
        );
        this.articleRepository.saveAndFlush(article);
    }

    @Override
    public ArticleDeleteEditViewModel getArticleToDelete(Long id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if(article.isPresent()){
            if(!this.isUserAuthorOrAdmin(article.get())){
                return null;
            }
            return article.map(serviceProd -> this.modelParser.convert(serviceProd, ArticleDeleteEditViewModel.class)).orElse(null);
        }
        return null;
    }

    @Override
    public ArticleBindingModel findById(Long id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if(article.isPresent()){
            if(!this.isUserAuthorOrAdmin(article.get())){
                return null;
            }
        }
        return article.map(article1 -> this.modelParser.convert(article1, ArticleBindingModel.class)).orElse(null);
    }

    @Override
    public void editArticle(Long id, ArticleBindingModel model) {
        Optional<Article> optionalArticle = this.articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            if(!this.isUserAuthorOrAdmin(optionalArticle.get())){
                return;
            }
            Article article = optionalArticle.get();
            article.setTitle(model.getTitle());
            article.setDate(new Date());
            article.setContent(model.getContent());

            this.articleRepository.save(article);
        }
    }

    @Override
    public void deleteArticle(Long id) {
        this.articleRepository.deleteById(id);
    }

    @Override
    public String incrementView(Long article_id) {
        Optional<Article> optionalArticle = this.articleRepository.findById(article_id);
        if(optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            article.setPageView(article.getPageView() + 1);
            this.articleRepository.saveAndFlush(article);
            return "" + article.getPageView();
        }
        return "";
    }

    @Override
    public List<ArticleViewModel> getArticlesMainPage() {
        List<Article> articles = this.articleRepository.findAll();
        List<ArticleViewModel> allArticles = new ArrayList<>();
        for (Article article : articles) {
            ArticleViewModel model = this.modelParser.convert(article,ArticleViewModel.class);
            allArticles.add(model);
        }
        return allArticles;
    }

    public boolean isUserAuthorOrAdmin(Article article) {
        return this.loggedInUser().getRoles().stream().anyMatch(roles-> roles.getName().equals("ROLE_ADMIN")) || this.isAuthor(article);
    }
}