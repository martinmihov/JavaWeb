package org.softuni.accounting.controllers;

import org.softuni.accounting.areas.blog.domain.models.view.ArticleHomeViewModel;
import org.softuni.accounting.areas.blog.services.ArticleService;
import org.softuni.accounting.areas.users.domain.models.view.UserOpinionViewModel;
import org.softuni.accounting.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController extends BaseController {

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public HomeController(ArticleService articleService,
                          UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView home(){

        List<ArticleHomeViewModel> viewArticles = this.articleService.getArticlesIndexPage();
        List<UserOpinionViewModel> theHolyTrinity = this.userService.getUsersOpinions();

        Object[] models = new Object[]{viewArticles,theHolyTrinity};

        return this.view("home/index",models,
                "viewArticles","theHolyTrinity");
    }

    @GetMapping("/about-us")
    public ModelAndView aboutUs(){
        return this.view("about/about-us");
    }
}
