package org.softuni.accounting.areas.users.controllers;

import org.softuni.accounting.areas.blog.services.ArticleService;
import org.softuni.accounting.areas.requests.services.ReplyService;
import org.softuni.accounting.areas.users.domain.models.binding.ProfileEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.ProfileUploadAvatarBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.controllers.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ArticleService articleService;
    private final ReplyService replyService;

    public UserController(UserService userService, ArticleService articleService, ReplyService replyService) {
        this.userService = userService;
        this.articleService = articleService;
        this.replyService = replyService;
    }

    @GetMapping("/register")
    public ModelAndView register(UserRegisterBindingModel model) {
        return this.view("users/register", "user", model);
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute(name = "user") UserRegisterBindingModel userRegisterBindingModel,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return this.view("/users/register", "user", userRegisterBindingModel);
        this.userService.registerUser(userRegisterBindingModel);
        return this.redirect("/users/login");
    }

    @GetMapping("/profile/{email}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profilePage(@PathVariable String email, @ModelAttribute ProfileViewModel model) {
        ProfileViewModel profile = this.userService.findProfile(email);
        if (profile == null) return this.redirect("/");
        Object[] models = new Object[]{
                profile,
                this.replyService.getAllRequestsReplies(profile.getRequests()),
//                this.requestService.getRequestsBySenderEmailOrderByIsRepliedDesc(profile.getEmail()),
                this.articleService.getArticlesByAuthor(profile)};
        return this.view("users/profile", models, "profile", "conversation", "authorArticles");
    }

    @PostMapping("/profile/{email}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView updateAvatar(@PathVariable String email, @Valid @ModelAttribute ProfileUploadAvatarBindingModel model
            , BindingResult bindingResult, @RequestParam("image") MultipartFile image) {
        if (bindingResult.hasErrors()) return this.view("users/profile", "profile", model);
        this.userService.uploadProfileAvatar(model, image);
        return this.redirect("/users/profile/{email}");
    }

    @GetMapping("/profile/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(@PathVariable String id, @ModelAttribute ProfileEditBindingModel model) {
        model = this.userService.findProfileById(id);
        if (model == null) return this.view("error/403");
        Object[] models = new Object[]{model, id};
        return this.view("users/edit-profile", models, "profileEdit", "profileId");
    }


    @PostMapping("/profile/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@PathVariable String id, @Valid @ModelAttribute(name = "profileEdit") ProfileEditBindingModel model,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Object[] models = new Object[]{model, id};
            return this.view("users/edit-profile", models, "profileEdit", "profileId");
        }
        this.userService.editProfile(id, model);

        return this.redirect("/");
    }


//    @PostMapping("/profile/{email}")
//    public ModelAndView editUser(@PathVariable String email, @Valid @ModelAttribute UserEditBindingModel editUserBindingModel,
//                                 BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            Object[] models = new Object[]{editUserBindingModel, email};
//            return this.view("users/profile", models, "profile" , "userEmail");
//        }
//        this.userService.editUser(email, editUserBindingModel);
//
//        return this.redirect("/profile/{email}");
//    }
}

