package org.softuni.accounting.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    public ModelAndView view(String viewName,Object viewModel){
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.getModel().put("model",viewModel);
        return modelAndView;
    }
}
