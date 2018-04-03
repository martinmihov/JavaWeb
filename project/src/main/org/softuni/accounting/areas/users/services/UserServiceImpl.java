package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.domain.models.binding.UserEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;
import org.softuni.accounting.areas.users.domain.models.view.UserViewModel;
import org.softuni.accounting.areas.users.repositories.RoleRepository;
import org.softuni.accounting.areas.users.repositories.UserRepository;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelParser modelParser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelParser modelParser) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelParser = modelParser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
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
    public User registerUser(@Valid UserRegisterBindingModel model) {
        User user = this.modelParser.convert(model, User.class);
        user.setPassword(this.passwordEncoder.encode(model.getPassword()));

        Role role = this.roleRepository.findByName("USER");
        role.getUsers().add(user);
        user.getRoles().add(role);

        this.roleRepository.save(role);
        return this.userRepository.save(user);
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
    public UserEditBindingModel findById(String id) {

        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            Set<Long> roleIds = new HashSet<>();
            UserEditBindingModel userEditBindingModel = this.modelParser.convert(user.get(), UserEditBindingModel.class);
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
    public void editUser(@Valid UserEditBindingModel model) {
        Optional<User> optionalUser = this.userRepository.findById(model.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(model.getUsername());
            if (model.getPassword() != null && !model.getPassword().isEmpty()) {
                user.setPassword(this.passwordEncoder.encode(model.getPassword()));
            }
            user.setEmail(model.getEmail());

            Set<Long> rolesIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());
            for (Long id : rolesIds) {
                if (!model.getRolesIds().contains(id)) {
                    user.getRoles().remove(this.roleRepository.getOne(id));
                }
            }
            for (Long id : model.getRolesIds()) {
                if (!rolesIds.contains(id)) {
                    Role role = this.roleRepository.getOne(id);
                    user.getRoles().add(role);
                    role.getUsers().add(user);
                    this.roleRepository.save(role);
                }
            }

            this.userRepository.save(user);
        }
    }
}
