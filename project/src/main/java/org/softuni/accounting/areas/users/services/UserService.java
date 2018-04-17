package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.binding.ProfileEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.ProfileUploadAvatarBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.softuni.accounting.areas.users.domain.models.view.UserOpinionViewModel;
import org.softuni.accounting.areas.users.domain.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

public interface UserService extends UserDetailsService {

    void registerUser(@Valid UserRegisterBindingModel model);

    List<UserViewModel> getAll();

    UserEditBindingModel findUserById(String id);

    ProfileEditBindingModel findProfileById(String id);

    ProfileViewModel findProfile(String email);

    void editUser(String id, UserEditBindingModel model); //void editUser(@Valid UserEditBindingModel model);

    void editProfile(String id, ProfileEditBindingModel model);

    // TODO : Make User A UserServiceModel
    List<User> findAllUsers();

    boolean deleteUser(String id);

    void save(User user);

    User findByEmail(String email);

    void uploadProfileAvatar(ProfileUploadAvatarBindingModel model, MultipartFile image);

    List<UserOpinionViewModel> getUsersOpinions();

    void reviveUser(String id);
}
