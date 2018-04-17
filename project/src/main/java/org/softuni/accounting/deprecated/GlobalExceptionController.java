//package org.softuni.accounting.controllers;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ControllerAdvice
//public class GlobalExceptionController extends BaseController {
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String generalException()
//    {
//        return "404";
//    }
//

//    @ExceptionHandler(RuntimeException.class)
//    public ModelAndView getException(RuntimeException e) {
//        String errorMessage =
//                e.getClass().isAnnotationPresent(ResponseStatus.class)
//                        ? e.getClass().getAnnotation(ResponseStatus.class).reason()
//                        : DEFAULT_ERROR_MESSAGE;
//
//        return this.view("error-template", "error", errorMessage);
//    }
//}

