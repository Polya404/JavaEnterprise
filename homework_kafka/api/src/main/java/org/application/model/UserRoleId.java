package org.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@Data
public class UserRoleId implements Serializable {
    private Long user;
    private Long role;
}
