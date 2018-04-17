package org.softuni.accounting.controllers;

import org.softuni.accounting.areas.blog.domain.models.view.ArticleViewModel;
import org.softuni.accounting.areas.blog.services.ArticleService;
import org.softuni.accounting.areas.products.domain.models.view.ServiceProdViewModel;
import org.softuni.accounting.areas.products.services.ServiceProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {

    private final ServiceProdService serviceProdService;
    private final ArticleService articleService;

    @Autowired
    public SearchController(ServiceProdService serviceProdService, ArticleService articleService) {
        this.serviceProdService = serviceProdService;
        this.articleService = articleService;
    }

    @GetMapping("/articles-by-name")
    public ModelAndView searchArticlesByName(@RequestParam(name = "articles-by-name") String title) {
        List<ArticleViewModel> articleSearchResults = this.articleService.searchArticlesByNameContaining(title);
        Object[] models = new Object[]{articleSearchResults, title};
        if (articleSearchResults.isEmpty()) return this.view("search/articles", "title", title);
        return this.view("search/articles", models, "posts", "title");
    }

    @GetMapping("/services-by-description")
    public ModelAndView searchServiceProdByDescription(@RequestParam(name = "services-by-description") String description) {
        List<ServiceProdViewModel> servicesSearchResult = this.serviceProdService.searchServiceProd(description);
        Object[] models = new Object[]{servicesSearchResult, description};
        if (servicesSearchResult.isEmpty()) return this.view("search/services", "description", description);
        return this.view("search/services", models, "services", "description");
    }
}
