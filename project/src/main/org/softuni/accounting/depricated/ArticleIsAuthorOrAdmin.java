//package org.softuni.accounting.depricated;
//
//import org.softuni.accounting.areas.blog.domain.entities.Article;
//import org.softuni.accounting.areas.users.domain.entities.users.User;
//import org.softuni.accounting.areas.users.services.UserService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//
//@Component
//public final class ArticleIsAuthorOrAdmin { // TODO IS THAT EVEN CORRECT
//
//    private static  UserService userService;
//
//    private static boolean isAuthor(Article article){
//        return Objects.equals(loggedInUser().getId(),
//                article.getAuthor().getId());
//    }
//
//    public static boolean isUserAuthorOrAdmin(Article article) {
//        return loggedInUser().getRoles().stream().anyMatch(roles-> roles.getName().equals("ROLE_ADMIN")) || isAuthor(article);
//    }
//    private static User loggedInUser(){
//        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal();
//        return userService.findByEmail(user.getUsername());
//    }
//}
