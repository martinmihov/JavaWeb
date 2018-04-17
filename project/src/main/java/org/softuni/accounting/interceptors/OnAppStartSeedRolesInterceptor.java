package org.softuni.accounting.interceptors;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.areas.users.services.RoleService;
import org.softuni.accounting.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OnAppStartSeedRolesInterceptor extends HandlerInterceptorAdapter {

    private final RoleService roleService;

    @Autowired
    public OnAppStartSeedRolesInterceptor(RoleService roleService, UserService userService) {
        this.roleService = roleService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (this.roleService.findAllRoles().size() == 0) {
            Role userRole = new Role();
            userRole.setName("USER");
            Role moderatorRole = new Role();
            moderatorRole.setName("MODERATOR");
            Role adminRole = new Role();
            adminRole.setName("ADMIN");

            this.roleService.save(userRole);
            this.roleService.save(moderatorRole);
            this.roleService.save(adminRole);
        }
    }

}
