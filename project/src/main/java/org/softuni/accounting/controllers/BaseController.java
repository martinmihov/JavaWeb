package org.softuni.accounting.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView view(String viewName, String attributeName, Object model) {

        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject(attributeName, model);

        return modelAndView;
    }

    protected ModelAndView view(String viewName, Object[] model, String ... attributeNames) {

        ModelAndView modelAndView = new ModelAndView(viewName);

        for (int i = 0; i < model.length; i++) {
            Object o = model[i];
            modelAndView.addObject(attributeNames[i],o);
        }
        return modelAndView;
    }

    protected ModelAndView view(String viewName){
        return new ModelAndView(viewName);
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:" + url);
    }
}
