package org.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.application.dto.UserRegistrationRequest;
import org.application.dto.UserRequestResponse;
import org.application.exception.UserNotFoundException;
import org.application.mapper.UserRegistrationRequestMapper;
import org.application.mapper.UserRequestResponseMapper;
import org.application.model.Roles;
import org.application.model.User;
import org.application.repository.UserRepository;
import org.application.repository.UserRoleRepository;
import org.application.service.RoleService;
import org.application.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static org.application.exception.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRequestResponseMapper requestResponseMapper;
    private final UserRegistrationRequestMapper registrationRequestMapper;


    @Override
    public UserRequestResponse createUser(UserRegistrationRequest user) {
        User entity = registrationRequestMapper.toEntity(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(entity);
        setRole(entity);
        return requestResponseMapper.toDto(saved);
    }

    private void setRole(User user) {
        Long unverifiedRoleId = roleService.findRoleByName(Roles.ROLE_USER.name().toUpperCase(Locale.ROOT)).getId();
        roleService.addRoleToUser(user.getId(), unverifiedRoleId);
    }

    @Override
    public UserRequestResponse findUserByEmail(String email) {
        return requestResponseMapper.toDto(userRepository.findByEmail(email));
    }

    @Override
    public UserRequestResponse findUserById(Long id) {
        return requestResponseMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND, id)));
    }

    @Override
    public void deleteUserById(Long id) {
        userRoleRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserRequestResponse> getAllUsers() {
        return requestResponseMapper.toDtos(userRepository.findAll());
    }

    @Override
    public UserRequestResponse updateUser(UserRequestResponse user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND, user.getId()));
        BeanUtils.copyProperties(user, existingUser, "id");
        return requestResponseMapper.toDto(userRepository.save(existingUser));
    }
}
