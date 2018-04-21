package org.softuni.accounting.areas.users.controllers;

import org.softuni.accounting.areas.blog.services.ArticleService;
import org.softuni.accounting.areas.requests.services.ReplyService;
import org.softuni.accounting.areas.users.domain.models.binding.ProfileEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.ProfileEditOpinionBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.ProfileUploadAvatarBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ArticleService articleService;
    private final ReplyService replyService;

    @Autowired
    public UserController(UserService userService,
                          ArticleService articleService,
                          ReplyService replyService) {

        this.userService = userService;
        this.articleService = articleService;
        this.replyService = replyService;
    }

    @GetMapping("/register")
    public ModelAndView register(UserRegisterBindingModel model) {
        return this.view("users/register", "user", model);
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute(name = "user") UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return this.view("/users/register", "user", userRegisterBindingModel);
        this.userService.registerUser(userRegisterBindingModel);

        return this.redirect("/users/login");
    }

    @GetMapping("/profile/{email}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profilePage(@PathVariable String email,
                                    @ModelAttribute ProfileViewModel model) {
        ProfileViewModel profile = this.userService.findProfile(email);

        if (profile == null) return this.redirect("/");

        Object[] models = new Object[]{
                profile,
                this.replyService.getAllRequestsReplies(profile.getRequests()),
                this.articleService.getArticlesByAuthor(profile)};

        return this.view("users/profile", models,
                "profile", "conversation", "authorArticles");
    }

    @PostMapping("/profile/{email}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView updateAvatar(@PathVariable String email,
                                     @Valid @ModelAttribute ProfileUploadAvatarBindingModel model,
                                     BindingResult bindingResult,
                                     @RequestParam("image") MultipartFile image) {

        if (bindingResult.hasErrors()) return this.view("users/profile", "profile", model);

        this.userService.uploadProfileAvatar(model, image);

        return this.redirect("/users/profile/{email}");
    }

    @GetMapping("/profile/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(@PathVariable String id,
                                    @ModelAttribute ProfileEditBindingModel model) {
        model = this.userService.findProfileById(id);

        if (model == null) return this.view("error/403");

        Object[] models = new Object[]{model, id};

        return this.view("users/edit-profile", models,
                "profileEdit", "profileId");
    }


    @PostMapping("/profile/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@PathVariable String id,
                                           @Valid @ModelAttribute(name = "profileEdit") ProfileEditBindingModel model,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Object[] models = new Object[]{model, id};
            return this.view("users/edit-profile", models,
                    "profileEdit", "profileId");
        }
        this.userService.editProfile(id, model);

        return this.redirect("/");
    }

    @GetMapping("/profile/edit-opinion/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileOpinion(@PathVariable String id,
                                           @ModelAttribute ProfileEditOpinionBindingModel model) {
        model = this.userService.findProfileOpinionById(id);

        if (model == null) return this.view("error/403");

        Object[] models = new Object[]{model, id};

        return this.view("users/edit-profile-opinion", models,
                "profileEditOpinion", "profileId");
    }


    @PostMapping("/profile/edit-opinion/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileOpinionConfirm(@PathVariable String id,
                                                  @Valid @ModelAttribute(name = "profileEditOpinion") ProfileEditOpinionBindingModel model,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Object[] models = new Object[]{model, id};
            return this.view("users/edit-profile-opinion", models,
                    "profileEditOpinion", "profileId");
        }
        this.userService.editProfileOpinion(id, model);

        return this.redirect("/");
    }
}

