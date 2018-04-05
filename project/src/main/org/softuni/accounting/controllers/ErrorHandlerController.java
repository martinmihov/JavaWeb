package org.softuni.accounting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandlerController extends BaseController{

    @GetMapping("/404")
    public String notFoundResource(){
        return "404";
    }
}
