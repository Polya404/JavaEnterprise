package org.application.lesson_15_springsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.application.lesson_15_springsecurity.model.Role;
import org.application.lesson_15_springsecurity.model.User;
import org.application.lesson_15_springsecurity.repository.RoleRepository;
import org.application.lesson_15_springsecurity.repository.UserRepository;
import org.application.lesson_15_springsecurity.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void addRoleToUser(long userid, long roleId) {
        User user = userRepository.findById(userid).orElseThrow();
        List<Role> roles = user.getRoles();
        roles.add(roleRepository.findById(roleId).orElseThrow());
        userRepository.save(user);
    }
}
