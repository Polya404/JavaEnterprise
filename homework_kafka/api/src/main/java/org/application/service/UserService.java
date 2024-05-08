package org.application.service;


import org.application.dto.UserRegistrationRequest;
import org.application.dto.UserRequestResponse;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserRequestResponse createUser(UserRegistrationRequest user);

    UserRequestResponse findUserByEmail(String email);

    UserRequestResponse findUserById(Long id);

    void deleteUserById(Long id);

    List<UserRequestResponse> getAllUsers();

    UserRequestResponse updateUser(UserRequestResponse user);
}
