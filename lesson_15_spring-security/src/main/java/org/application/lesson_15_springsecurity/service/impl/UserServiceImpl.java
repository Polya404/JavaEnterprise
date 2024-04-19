package org.application.lesson_15_springsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.application.lesson_15_springsecurity.model.Roles;
import org.application.lesson_15_springsecurity.repository.UserRepository;
import org.application.lesson_15_springsecurity.model.User;
import org.application.lesson_15_springsecurity.service.RoleService;
import org.application.lesson_15_springsecurity.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        setRole(user);
    }

    private void setRole(User user) {
        Long unverifiedRoleId = roleService.findRoleByName(Roles.ROLE_USER.name().toUpperCase(Locale.ROOT)).getId();
        roleService.addRoleToUser(user.getId(), unverifiedRoleId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
