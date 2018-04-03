package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.binding.UserEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.domain.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;
import java.util.List;

public interface UserService extends UserDetailsService {

    User registerUser(@Valid UserRegisterBindingModel model);

    List<UserViewModel> getAll();

    UserEditBindingModel findById(String id);

    void editUser(@Valid UserEditBindingModel model);
}
