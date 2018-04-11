package org.softuni.accounting.areas.users.controllers;

import org.softuni.accounting.areas.requests.domain.models.view.ReplyViewModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;
import org.softuni.accounting.areas.requests.services.ReplyService;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ReplyService replyService;

    public UserController(UserService userService, ReplyService replyService) {
        this.userService = userService;
        this.replyService = replyService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return this.view("users/register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute(name = "register") UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("register", userRegisterBindingModel);
            return this.redirect("/users/register");
        }
        this.userService.registerUser(userRegisterBindingModel);
        return this.redirect("/users/login");
    }

    @GetMapping("/profile/{email}")
    public ModelAndView profilePage(@PathVariable String email, @ModelAttribute ProfileViewModel model) {
        ProfileViewModel profile = this.userService.findProfile(email);
        if (profile == null) return this.redirect("/");
        Object[] models = new Object[]{profile.getUsername(), this.replyService.getAllRequestsReplies(profile.getRequests())};
        return this.view("users/profile", models, "username", "conversation");
    }
}

