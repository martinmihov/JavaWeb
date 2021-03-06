package org.softuni.accounting.areas.blog.services;

import org.softuni.accounting.areas.blog.domain.entities.Article;
import org.softuni.accounting.areas.blog.domain.models.binding.ArticleBindingModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleDeleteEditViewModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleHomeViewModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleViewModel;
import org.softuni.accounting.areas.blog.repositories.ArticleRepository;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final ModelParser modelParser;

    private boolean isAuthor(Article article) {

        return
                Objects.equals(loggedInUser().getId(),
                article.getAuthor().getId());
    }

    private User loggedInUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return this.userService.findByEmail(user.getUsername());
    }

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              UserService userService,
                              ModelParser modelParser) {

        this.articleRepository = articleRepository;
        this.userService = userService;
        this.modelParser = modelParser;
    }

    public boolean isUserAuthorOrAdmin(Article article) {

        return
                this.loggedInUser()
                .getRoles()
                .stream()
                .anyMatch(roles -> roles.getName().equals("ADMIN")) || this.isAuthor(article);
    }

    @Override
    public ArticleViewModel viewArticle(Long id) {

        Optional<Article> optionalArticle = this.articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            return this.modelParser.convert(article, ArticleViewModel.class);
        }
        return null;
    }

    @Override
    public List<ArticleViewModel> searchArticlesByNameContaining(String title) {

        List<Article> articlesByTitle = this.articleRepository.findArticlesByTitleContainingOrderByTitle(title);
        List<ArticleViewModel> viewSearch = new ArrayList<>();

        for (Article article : articlesByTitle) {
            ArticleViewModel view = this.modelParser.convert(article,ArticleViewModel.class);
            viewSearch.add(view);
        }
        return viewSearch;
    }

    @Override
    public ArticleDeleteEditViewModel getArticleToEditOrDelete(Long id) {

        Optional<Article> article = this.articleRepository.findById(id);

        if (article.isPresent()) {
            if (!this.isUserAuthorOrAdmin(article.get())) {
                return null;
            }
            return article.map(serviceProd ->
                    this.modelParser.convert(serviceProd, ArticleDeleteEditViewModel.class)).orElse(null);
        }
        return null;
    }

    @Override
    public void editArticle(Long id, ArticleBindingModel model,MultipartFile image) {
        Optional<Article> optionalArticle = this.articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            if (!this.isUserAuthorOrAdmin(optionalArticle.get())) {
                return;
            }
            Article article = optionalArticle.get();
            article.setTitle(model.getTitle());
            article.setDate(new Date());
            article.setContent(model.getContent());
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
                String imagesPath = "\\src\\main\\resources\\static\\images\\";
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
            article.setImagePath(imagePath);

            this.articleRepository.save(article);
        }
    }

    @Override
    public void deleteArticle(Long id) {
        this.articleRepository.deleteById(id);
    }

    public List<ArticleHomeViewModel> getArticlesIndexPage() {

        List<Article> articles = this.articleRepository.findTop3ByImagePathNotNullOrderByDateDesc();
        List<ArticleHomeViewModel> homeArticles = new ArrayList<>();

        for (Article article : articles) {
            ArticleHomeViewModel homeArticle = this.modelParser.convert(article, ArticleHomeViewModel.class);
            homeArticles.add(homeArticle);
        }
        return homeArticles;
    }

    @Override
    public List<ArticleHomeViewModel> getArticlesByAuthor(ProfileViewModel author) {

        User user = this.userService.findByEmail(author.getEmail());

        List<Article> articlesByAuthor = this.articleRepository.findArticlesByAuthor(user);
        List<ArticleHomeViewModel> articlesByAuthorView = new ArrayList<>();

        for (Article article : articlesByAuthor) {
            ArticleHomeViewModel articleHomeViewModel = this.modelParser.convert(article, ArticleHomeViewModel.class);
            articlesByAuthorView.add(articleHomeViewModel);
        }
        return articlesByAuthorView;
    }

    @Override
    public List<ArticleViewModel> getSixMostPopularArticles() {

        List<Article> mostPopularArticles = this.articleRepository.findTop6ByOrderByPageViewDesc();
        List<ArticleViewModel> mostPopular = new ArrayList<>();

        for (Article mostPopularArticle : mostPopularArticles) {
            ArticleViewModel viewModel = this.modelParser.convert(mostPopularArticle,ArticleViewModel.class);
            mostPopular.add(viewModel);
        }
        return mostPopular;
    }

    @Override
    public List<ArticleViewModel> getSixMostRecentArticles() {

        List<Article> mostRecentArticles = this.articleRepository.findTop6ByOrderByDateDesc();
        List<ArticleViewModel> mostRecent = new ArrayList<>();

        for (Article mostRecentArticle : mostRecentArticles) {
            ArticleViewModel viewModel = this.modelParser.convert(mostRecentArticle,ArticleViewModel.class);
            mostRecent.add(viewModel);
        }
        return mostRecent;
    }

    @Override
    public Page<ArticleViewModel> findAllByPage(Pageable pageable) {

        return this.articleRepository
                .findAll(pageable).map(x ->
                        this.modelParser.convert(x, ArticleViewModel.class));
    }

    @Override
    public void savePost(ArticleBindingModel model, MultipartFile image) {

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
            String imagesPath = "\\src\\main\\resources\\static\\images\\";
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
    public String incrementView(Long article_id) {

        Optional<Article> optionalArticle = this.articleRepository.findById(article_id);

        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setPageView(article.getPageView() + 1);
            this.articleRepository.saveAndFlush(article);
            return "" + article.getPageView();
        }
        return "";
    }
}