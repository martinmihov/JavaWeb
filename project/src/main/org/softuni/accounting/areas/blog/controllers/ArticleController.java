package org.softuni.accounting.areas.blog.controllers;

import org.softuni.accounting.areas.blog.domain.entities.Article;
import org.softuni.accounting.areas.blog.domain.models.binding.ArticleBindingModel;
import org.softuni.accounting.areas.blog.domain.models.view.ArticleViewModel;
import org.softuni.accounting.areas.blog.services.ArticleService;
import org.softuni.accounting.controllers.BaseController;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/blog")
public class ArticleController extends BaseController {

    private final ArticleService articleService;
    private final ModelParser modelParser;

    @Autowired
    public ArticleController(ArticleService articleService, ModelParser modelParser) {
        this.articleService = articleService;
        this.modelParser = modelParser;
    }

    @GetMapping("/")
    public ModelAndView blogMainPage() {
        return this.view("blog/main-page.html","articles",this.articleService.getArticlesMainPage());
    }

    @GetMapping("/articles/{id}")
    public ModelAndView articleDetails(@PathVariable Long id) {
        ArticleViewModel model = this.articleService.viewArticle(id);
        Article article = this.modelParser.convert(model,Article.class);
        Object[] models = new Object[]{model,this.articleService.isUserAuthorOrAdmin(article)};
        return this.view("blog/view-article",models,"article","userIsAuthorOrAdmin");
    }

    @GetMapping("/articles/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createArticle() {
        return this.view("blog/create-article");
    }

    @PostMapping("/articles/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createArticleConfirm(@Valid @ModelAttribute ArticleBindingModel articleBindingModel, BindingResult bindingResult, @RequestParam("image") MultipartFile image) {
        if (bindingResult.hasErrors()) return this.view("blog/create-article", "article", articleBindingModel);
        this.articleService.savePost(articleBindingModel, image);
        return this.redirect("/");
    }

    @GetMapping("/articles/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteArticle(@PathVariable Long id) {
        return this.view("blog/delete-article", "article", this.articleService.getArticleToDelete(id));
    }

    @PostMapping("/articles/delete/{id}")
    public ModelAndView deleteArticleConfirm(@PathVariable Long id) {
        this.articleService.deleteArticle(id);
        return this.redirect("/blog/");
    }

    @GetMapping("/articles/edit/{id}")
    public ModelAndView editArticle(@PathVariable Long id, @ModelAttribute ArticleBindingModel model) {
        model = this.articleService.findById(id);
        Object[] models = new Object[]{model, id};
        return this.view("blog/edit-article", models, "article", "articleId");
    }

    @PostMapping("/articles/edit/{id}")
    public ModelAndView editArticleConfirm(@PathVariable Long id, @Valid @ModelAttribute ArticleBindingModel model,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Object[] models = new Object[]{model, id};
            return this.view("blog/edit-article", models, "article","articleId");
        }
        this.articleService.editArticle(id, model);

        return this.redirect("/blog/");
    }

    @GetMapping("/articles/count/{article_id}")
    @ResponseBody
    public String count(@PathVariable Long article_id) {
        return this.articleService.incrementView(article_id);
    }
}