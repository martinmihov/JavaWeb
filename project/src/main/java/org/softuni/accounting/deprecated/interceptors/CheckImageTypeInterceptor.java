//package org.softuni.accounting.deprecated.interceptors;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Parameter;
//import java.util.Arrays;
//import java.util.Optional;
//
//@Component
//public class CheckImageTypeInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Parameter[] parameters = handlerMethod.getMethod().getParameters();
//
//        Optional<Parameter> parameter = Arrays.stream(parameters).filter(mp -> mp.getType().equals(MultipartFile.class))
//                .findFirst();
//        if (parameter.isPresent()) {
//            Parameter p = parameter.get();
//            MultipartFile file = (MultipartFile) request.getAttribute(p.getName());
//            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            if (ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg") || ext.equals("gif")) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        return super.preHandle(request, response, handler);
//    }
//}
