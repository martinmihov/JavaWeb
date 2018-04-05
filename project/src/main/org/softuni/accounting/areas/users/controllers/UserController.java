package org.softuni.accounting.areas.users.controllers;

import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
       return this.view("users/register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute(name = "register") UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerViewModel", bindingResult);
            redirectAttributes.addFlashAttribute("register", userRegisterBindingModel);
            modelAndView.setViewName("redirect:/users/register");
        } else {
            this.userService.registerUser(userRegisterBindingModel);
            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }
}
