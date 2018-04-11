package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.requests.domain.models.view.ReplyViewModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.binding.UserEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.softuni.accounting.areas.users.domain.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {

    void registerUser(@Valid UserRegisterBindingModel model);

    List<UserViewModel> getAll();

    UserEditBindingModel findById(String id);

    ProfileViewModel findProfile(String email);

    void editUser(String id, UserEditBindingModel model); //void editUser(@Valid UserEditBindingModel model);
    // TODO : Make User A UserServiceModel
    List<User> findAll();

    void save(User user);

    User findByEmail(String email);
}
