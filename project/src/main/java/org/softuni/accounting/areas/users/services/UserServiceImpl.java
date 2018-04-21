package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.binding.*;
import org.softuni.accounting.areas.users.domain.models.view.ProfileViewModel;
import org.softuni.accounting.areas.users.domain.models.view.UserOpinionViewModel;
import org.softuni.accounting.areas.users.domain.models.view.UserViewModel;
import org.softuni.accounting.areas.users.repositories.UserRepository;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ModelParser modelParser;

    private boolean isTheSame(User user) {

        return Objects.equals(loggedInUser().getId(),
                user.getId());
    }

    private User loggedInUser(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return this.findByEmail(user.getUsername());
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           PasswordEncoder passwordEncoder,
                           ModelParser modelParser) {

        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.modelParser = modelParser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmailAndDeletedOnIsNull(email);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        } else {
            Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                    .collect(Collectors.toSet());

            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
    }

    @Override
    public void registerUser(@Valid UserRegisterBindingModel model) {

        User user = this.modelParser.convert(model, User.class);
        user.setPassword(this.passwordEncoder.encode(model.getPassword()));

        Role role = this.roleService.getByName("USER");

        role.getUsers().add(user);
        user.getRoles().add(role);

        this.roleService.save(role);
        this.userRepository.save(user);
    }

    @Override
    public List<UserViewModel> getAll() {

        List<User> users = this.userRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();

        for (User user : users) {
            UserViewModel model = this.modelParser.convert(user, UserViewModel.class);
            userViewModels.add(model);
        }
        return userViewModels;
    }

    @Override
    public UserEditBindingModel findUserById(String id) {

        Optional<User> user = this.userRepository.findById(id);

        if (user.isPresent()) {
            UserEditBindingModel userEditBindingModel = this.modelParser.convert(user.get(), UserEditBindingModel.class);

            Set<Long> roleIds = new HashSet<>();

            for (Role role : user.get().getRoles()) {
                roleIds.add(role.getId());
            }

            userEditBindingModel.setPassword("");
            userEditBindingModel.setConfirmPassword("");
            userEditBindingModel.setRolesIds(roleIds);

            return userEditBindingModel;
        }
        return null;
    }

    @Override
    public ProfileEditBindingModel findProfileById(String id) {

        Optional<User> user = this.userRepository.findById(id);

        if (user.isPresent()) {

            if(!this.isTheSame(user.get())) return null;

            ProfileEditBindingModel editProfile = this.modelParser.convert(user.get(), ProfileEditBindingModel.class);
            editProfile.setUsername(user.get().getUsername());
            editProfile.setEmail(user.get().getEmail());
            editProfile.setPassword("");
            editProfile.setConfirmPassword("");

            return editProfile;
        }
        return null;
    }

    @Override
    public void editUser(String id, UserEditBindingModel model) {

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            user.setEmail(model.getEmail());
            user.setUsername(model.getUsername());
            user.setOpinion(model.getOpinion());

            if (model.getPassword() != null && !model.getPassword().isEmpty()) {
                user.setPassword(this.passwordEncoder.encode(model.getPassword()));
            }
            Set<Long> rolesIds =

                    user
                    .getRoles()
                    .stream()
                    .map(Role::getId)
                    .collect(Collectors.toSet());

            for (Long roleId : rolesIds) {
                if (!model.getRolesIds().contains(roleId)) {
                    user.getRoles().remove(this.roleService.getOneById(roleId));
                }
            }
            for (Long roleId : model.getRolesIds()) {
                if (!rolesIds.contains(roleId)) {
                    Role role = this.roleService.getOneById(roleId);
                    user.getRoles().add(role);
                    role.getUsers().add(user);
                    this.roleService.save(role);
                }
            }
            this.userRepository.save(user);
        }
    }

    @Override
    public void editProfile(String id, ProfileEditBindingModel model) {

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            user.setEmail(model.getEmail());
            user.setUsername(model.getUsername());

            if (model.getPassword() != null && !model.getPassword().isEmpty()) {
                user.setPassword(this.passwordEncoder.encode(model.getPassword()));
            }
            this.userRepository.save(user);
        }
    }
    @Override
    public ProfileEditOpinionBindingModel findProfileOpinionById(String id) {

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if(!this.isTheSame(user)) return null;

            ProfileEditOpinionBindingModel editProfile =
                    this.modelParser.convert(user, ProfileEditOpinionBindingModel.class);

            editProfile.setOpinion(user.getOpinion());

            return editProfile;
        }
        return null;
    }

    @Override
    public ProfileEditOpinionBindingModel findUserOpinionById(String id) {

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            ProfileEditOpinionBindingModel editProfile = this.modelParser.convert(user, ProfileEditOpinionBindingModel.class);
            editProfile.setOpinion(user.getOpinion());

            return editProfile;
        }
        return null;
    }

    @Override
    public void editProfileOpinion(String id, ProfileEditOpinionBindingModel model) {

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setOpinion(model.getOpinion());
            this.userRepository.save(user);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public boolean banUser(String id) {
        User user = this.userRepository.getOne(id);
        user.setDeletedOn(new Date());
        this.userRepository.save(user);

        return true;
    }

    @Override
    public void reviveUser(String id) {
        User user = this.userRepository.getOne(id);
        user.setDeletedOn(null);
        this.userRepository.save(user);
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void uploadProfileAvatar(ProfileUploadAvatarBindingModel model, MultipartFile image) {

        User userEntity = this.findByEmail(model.getEmail());

        String imagePath = null;
        String[] allowedContentTypes = {
                "image/png",
                "image/jpg",
                "image/jpeg",
                "image/gif"
        };
        boolean isContentTypeAllowed = Arrays.asList(allowedContentTypes)
                .contains(model.getImage().getContentType());

        if (isContentTypeAllowed) {
            String imagesPath = "\\src\\main\\resources\\static\\images\\";
            String imagePathRoot = System.getProperty("user.dir");
            String imageSaveDirectory = imagePathRoot + imagesPath;
            String filename = model.getImage().getOriginalFilename();
            String savePath = imageSaveDirectory + filename;
            File imageFile = new File(savePath);
            try {
                model.getImage().transferTo(imageFile);
                imagePath = "/images/" + filename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userEntity.setImagePath(imagePath);
        this.userRepository.saveAndFlush(userEntity);
    }


    @Override
    public List<UserOpinionViewModel> getUsersOpinions() {

        List<User> usersWithOpinions = this.userRepository.

            findDistinctTop3ByImagePathNotNullAndOpinionNotNullAndArticlesNotNullAndDeletedOnIsNullOrderByArticlesAsc();

        List<UserOpinionViewModel> usersIndexPage = new ArrayList<>();

        for (User usersWithOpinion : usersWithOpinions) {
            UserOpinionViewModel user = this.modelParser.convert(usersWithOpinion, UserOpinionViewModel.class);
            usersIndexPage.add(user);
        }
        return usersIndexPage;
    }

    @Override
    public ProfileViewModel findProfile(String email) {
        User user = this.userRepository.findByEmail(email);

        return this.modelParser.convert(user, ProfileViewModel.class);
    }
}
