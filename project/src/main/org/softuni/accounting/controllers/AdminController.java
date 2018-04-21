package org.softuni.accounting.controllers;

import org.softuni.accounting.areas.products.domain.models.binding.ServiceProdAddBindingModel;
import org.softuni.accounting.areas.products.domain.models.view.ServiceProdViewModel;
import org.softuni.accounting.areas.products.services.ServiceProdService;
import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.requests.domain.models.binding.ReplyBindingModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestAndReplyViewModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;
import org.softuni.accounting.areas.requests.services.ReplyService;
import org.softuni.accounting.areas.requests.services.RequestService;
import org.softuni.accounting.areas.users.domain.models.binding.UserEditBindingModel;
import org.softuni.accounting.areas.users.services.RoleService;
import org.softuni.accounting.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admins")
public class AdminController extends BaseController {

    private final UserService userService;
    private final RoleService roleService;
    private final ServiceProdService serviceProdService;
    private final RequestService requestService;
    private final ReplyService replyService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, ServiceProdService serviceProdService, RequestService requestService, ReplyService replyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.serviceProdService = serviceProdService;
        this.requestService = requestService;
        this.replyService = replyService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView viewAllUsers() {
        return this.view("admins/all-users", "users", this.userService.getAll());
    }

    @GetMapping("/edit/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView editUser(@PathVariable String id, @ModelAttribute UserEditBindingModel userEditBindingModel) {
        userEditBindingModel = this.userService.findUserById(id);
        Object[] models = new Object[]{userEditBindingModel, this.roleService.findAllRoles(), id};
        return this.view("admins/edit-user", models, "user", "roles", "userId");
    }


    @PostMapping("/edit/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView editUserConfirm(@PathVariable String id, @Valid @ModelAttribute(name = "user") UserEditBindingModel editUserBindingModel,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Object[] models = new Object[]{editUserBindingModel, this.roleService.findAllRoles(), id};
            return this.view("admins/edit-user", models, "user", "roles", "userId");
        }
        this.userService.editUser(id, editUserBindingModel);

        return this.redirect("/admins/users");
    }

    @GetMapping("/services")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView viewAllServices() {
        return this.view("admins/all-services", "services", this.serviceProdService.getAllServices());
    }

    @GetMapping("/services/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView addService(ServiceProdViewModel model) {
        return this.view("admins/add-service", "service", model);
    }

    @PostMapping("/services/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView addServiceConfirm(@Valid @ModelAttribute("service") ServiceProdAddBindingModel serviceProdModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return this.view("admins/add-service", "service", serviceProdModel);
        this.serviceProdService.addService(serviceProdModel);
        return this.redirect("/admins/services");
    }

    @GetMapping("/services/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView deleteService(@PathVariable Long id) {
        return this.view("admins/delete-service", "service", this.serviceProdService.getServiceToDelete(id));
    }

    @PostMapping("/services/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView deleteServiceConfirm(@PathVariable Long id) {
        this.serviceProdService.deleteService(id);
        return this.redirect("/admins/services");
    }

    @GetMapping("/requests")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView allRequests() {
        return this.view("admins/all-requests", "requests", this.requestService.getAllRequests());
    }

    @GetMapping("/reply-to-requests")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView replyToRequests() {
        return this.view("admins/reply-to-requests", "requests", this.requestService.getAllRequests());
    }

    @GetMapping("/reply-to-requests/response/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView respondRequest(@PathVariable Long id, @ModelAttribute RequestViewModel model) {
        model = this.requestService.findRequestById(id);
        Object[] models = new Object[]{model, id};
        return this.view("admins/request-reply", models, "replyToRequest", "requestId");
    }

    @PostMapping("/reply-to-requests/response")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ModelAndView respondRequestConfirm(@RequestParam(name = "id") Long id, @Valid @ModelAttribute ReplyBindingModel model,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.view("admins/request-reply", "replyToRequest", model);
        }
        Request requestById = this.requestService.findById(id); // TODO: find a better way
        model.setRequest(requestById);
//        requestById.getReplies().add(model);
        this.replyService.saveReply(model);
        return this.redirect("/admins/reply-to-requests");
    }

//    @GetMapping("/reply-to-requests/response/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
//    public ModelAndView respondRequest(@PathVariable Long id, @ModelAttribute RequestAndReplyViewModel model){
//        Object[] models = new Object[]{model, id};
//        return this.view("admins/request-reply",models, "replyToRequest", "requestId");
//    }
//
//    @PostMapping("/reply-to-requests/response/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
//    public ModelAndView respondRequestConfirm(@PathVariable Long id, @Valid @ModelAttribute(name = "replyToRequest") ReplyBindingModel model,
//                                              BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return this.view("admins/request-reply","replyToRequest",model);
//        }
//        Request requestById = this.requestService.findById(id); // TODO: find a better way
//        model.setRequest(requestById);
//        this.replyService.saveReply(model);
//        return this.redirect("/admins/reply-to-requests");
//    }


    @PostMapping("/requests/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView deleteRequestConfirm(@PathVariable Long id) {
        this.requestService.deleteRequest(id);
        return this.redirect("/admins/requests");
    }
}