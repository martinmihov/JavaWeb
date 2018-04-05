package org.softuni.accounting.areas.users.controllers.admins;

import org.softuni.accounting.areas.products.domain.models.binding.ServiceProdAddBindingModel;
import org.softuni.accounting.areas.products.domain.models.view.ServiceProdViewModel;
import org.softuni.accounting.areas.products.services.ServiceProdService;
import org.softuni.accounting.areas.users.domain.models.binding.UserEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.service.RoleServiceModel;
import org.softuni.accounting.areas.users.domain.models.view.UserViewModel;
import org.softuni.accounting.areas.users.services.RoleService;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController extends BaseController {

    private final UserService userService;
    private final RoleService roleService;
    private final ServiceProdService serviceProdService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, ServiceProdService serviceProdService) {
        this.userService = userService;
        this.roleService = roleService;
        this.serviceProdService = serviceProdService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView viewAllUsers(ModelAndView modelAndView) {
        return this.view("admins/all-users", "users", this.userService.getAll());
    }

    @GetMapping("/edit/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView editUser(@PathVariable String id, @ModelAttribute UserEditBindingModel userEditBindingModel) {
        userEditBindingModel = this.userService.findById(id);
        Object[] models = {userEditBindingModel, this.roleService.findAllRoles(), id};
        return this.view("admins/edit-user", models, "user", "roles", "userId");
    }


    @PostMapping("/edit/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView editUser(@PathVariable String id, @Valid @ModelAttribute UserEditBindingModel editUserBindingModel,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Object[] models = {editUserBindingModel, this.roleService.findAllRoles(), id};
            return this.view("admins/edit-user", models, "user", "roles", "userId");
        }
        this.userService.editUser(id, editUserBindingModel);

        return this.redirect("/admins/users");
    }

    @GetMapping("/services")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView viewAllServices() {
        return this.view("admins/all-services", "services", this.serviceProdService.getAllServices());
    }

    @GetMapping("/services/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView addVirus(ModelAndView modelAndView) {
        ServiceProdAddBindingModel model = new ServiceProdAddBindingModel();
        modelAndView.addObject("service", model);
        modelAndView.setViewName("admins/add-service");
        return modelAndView;
    }

    @PostMapping("/services/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addVirusConfirm(@Valid @ModelAttribute("service") ServiceProdAddBindingModel serviceProdModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("service", serviceProdModel);
            return "admins/add-service";
        }
        this.serviceProdService.addService(serviceProdModel);
        return "redirect:/admins/services";
    }

    @GetMapping("/services/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView deleteVirus(@PathVariable Long id) {
        return this.view("admins/delete-service", "service", this.serviceProdService.getServiceToDelete(id));
    }

    @PostMapping("/services/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView deleteVirusConfirm(@PathVariable Long id) {
        this.serviceProdService.deleteService(id);
        return this.redirect("/admins/services");
    }
}


//    @GetMapping("/edit/user/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public ModelAndView editUser(@PathVariable String id, ModelAndView modelAndView) {
//
//        UserEditBindingModel userEditBindingModel = this.userService.findById(id);
//        modelAndView.setViewName("admins/edit-user");
//
//        List<RoleServiceModel> roles = this.roleService.findAllRoles();
//
//        modelAndView.addObject("roles", roles);
//
//        modelAndView.addObject("user", userEditBindingModel);
//
//        return modelAndView;
//    }


//
//    @PostMapping("/edit/user/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ModelAndView editUser(@PathVariable String id, @Valid @ModelAttribute(name = "user") UserEditBindingModel editUserBindingModel,
//                                 BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
//            redirectAttributes.addFlashAttribute("user", editUserBindingModel);
//            modelAndView.setViewName("redirect:/admins/all-users");
//        } else {
//            this.userService.editUser(editUserBindingModel);
//
//            modelAndView.setViewName("admins/all-users");
//        }
//
//        return modelAndView;
//    }


//    @PostMapping("/edit/user/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public String editUser(@Valid @ModelAttribute(name = "user") UserEditBindingModel editUserBindingModel,
//                           BindingResult bindingResult, Model model ,@PathVariable String id) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("user", bindingResult);
//            model.addAttribute("roles",this.roleService.findAllRoles());
//            return "admins/edit-user";
//        }
//        this.userService.editUser(editUserBindingModel);
//
//        return "redirect:/admins/users";
//    }


//    @PostMapping("/edit/user/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public String editUser(@Valid @ModelAttribute("user") UserEditBindingModel userModel, BindingResult bindingResult, Model model, @PathVariable String id) {
//        System.out.println("I AM IN EDIT USERS POST");
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("user", userModel);
//            model.addAttribute("roles", this.roleService.findAllRoles());
//            return "admins/edit-user";
//        }
//
//        this.userService.editUser(userModel);
//
//        return "redirect:/admins/users";
//    }