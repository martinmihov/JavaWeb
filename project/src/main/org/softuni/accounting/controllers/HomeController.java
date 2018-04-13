package org.softuni.accounting.controllers;


import org.softuni.accounting.areas.blog.domain.models.view.ArticleHomeViewModel;
import org.softuni.accounting.areas.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController extends BaseController {

    private final ArticleService articleService;

    @Autowired
    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public ModelAndView homeView(){
        List<ArticleHomeViewModel> viewArticles = this.articleService.getArticlesByOrderByDateDesc();
        int size = viewArticles.size();
        Object[] models = new Object[]{viewArticles,size};
        return this.view("home/index",models,"viewArticles","size");
    }
}
