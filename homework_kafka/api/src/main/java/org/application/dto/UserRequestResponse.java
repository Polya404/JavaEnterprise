package org.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.application.exception.ErrorMessage;

import java.time.LocalDate;

@Data
public class UserRequestResponse {

    @NotBlank(message = ErrorMessage.NAME_DOES_NOT_EXIST)
    protected String firstName;

    @NotBlank(message = ErrorMessage.NAME_DOES_NOT_EXIST)
    protected String lastName;

    @NotBlank
    @Email(message = ErrorMessage.EMAIL_ADDRESS_NOT_VALID)
    protected String email;

    @Past(message = ErrorMessage.INVALID_AGE)
    protected LocalDate birthDate;

    protected String address;

    @Pattern(regexp = "^([+]{1})[0-9]{8,17}$", message = ErrorMessage.PHONE_NUMBER_NOT_VALID)
    protected String phoneNumber;

    private Long id;
}

