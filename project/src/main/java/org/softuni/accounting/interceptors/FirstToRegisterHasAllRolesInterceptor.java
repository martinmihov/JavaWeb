package org.softuni.accounting.interceptors;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.service.RoleServiceModel;
import org.softuni.accounting.areas.users.services.RoleService;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FirstToRegisterHasAllRolesInterceptor extends HandlerInterceptorAdapter {

    private final UserService userService;
    private final RoleService roleService;
    private final ModelParser modelParser;

    @Autowired
    public FirstToRegisterHasAllRolesInterceptor(UserService userService, RoleService roleService, ModelParser modelParser) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelParser = modelParser;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (this.userService.findAllUsers().size() == 1) {
            User user = this.userService.findAllUsers().get(0);
            List<RoleServiceModel> serviceModels = this.roleService.findAllRoles();
            Set<Role> roles = new HashSet<>();
            for (RoleServiceModel serviceModel : serviceModels) {
                roles.add(this.modelParser.convert(serviceModel,Role.class));
            }
            user.setRoles(roles);

            this.userService.save(user);
        }
    }
}
