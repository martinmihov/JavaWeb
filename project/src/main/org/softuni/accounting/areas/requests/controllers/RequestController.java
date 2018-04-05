package org.softuni.accounting.areas.requests.controllers;

import org.softuni.accounting.areas.requests.domain.models.binding.RequestSendBindingModel;
import org.softuni.accounting.areas.requests.services.RequestService;
import org.softuni.accounting.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RequestController extends BaseController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/contact-us")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView contactForm(){
        return this.view("requests/request");
    }

    @PostMapping("/contact-us")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView contactFormConfirm(@Valid @ModelAttribute RequestSendBindingModel requestModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            this.view("request/request","requestForm",requestModel);
        }
        this.requestService.saveMessage(requestModel);
        return this.redirect("/");
    }
}
