package org.application.controller;

import lombok.RequiredArgsConstructor;
import org.application.dto.UserRegistrationRequest;
import org.application.dto.UserRequestResponse;
import org.application.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.application.exception.ErrorMessage.EXIST_USER;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class AuthController {
    private final UserService userService;

    @PostMapping("/save")
    public UserRequestResponse registration(@RequestBody UserRegistrationRequest user,
                                            BindingResult result) {
        UserRequestResponse existingUser = userService.findUserByEmail(user.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email",
                    EXIST_USER);
        }
        return userService.createUser(user);
    }
}
