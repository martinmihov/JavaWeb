package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.binding.*;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.softuni.accounting.areas.users.domain.models.view.UserOpinionViewModel;
import org.softuni.accounting.areas.users.domain.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    void registerUser(@Valid UserRegisterBindingModel model);

    void editUser(String id, UserEditBindingModel model);

    void editProfile(String id, ProfileEditBindingModel model);

    void editProfileOpinion(String id, ProfileEditOpinionBindingModel model);

    void uploadProfileAvatar(ProfileUploadAvatarBindingModel model, MultipartFile image);

    void reviveUser(String id);

    boolean banUser(String id);

    User findByEmail(String email);

    UserEditBindingModel findUserById(String id);

    ProfileViewModel findProfile(String email);

    ProfileEditBindingModel findProfileById(String id);

    ProfileEditOpinionBindingModel findUserOpinionById(String id);

    ProfileEditOpinionBindingModel findProfileOpinionById(String id);

    List<User> findAllUsers();

    List<UserOpinionViewModel> getUsersOpinions();

    List<UserViewModel> getAll();

}
