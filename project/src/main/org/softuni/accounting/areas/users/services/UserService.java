package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.binding.UserEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.domain.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;
import java.util.List;

public interface UserService extends UserDetailsService {

    void registerUser(@Valid UserRegisterBindingModel model);

    List<UserViewModel> getAll();

    UserEditBindingModel findById(String id);

    void editUser(String id, UserEditBindingModel model); //void editUser(@Valid UserEditBindingModel model);
    // TODO : Make User A UserServiceModel
    List<User> findAll();

    void save(User user);
}
