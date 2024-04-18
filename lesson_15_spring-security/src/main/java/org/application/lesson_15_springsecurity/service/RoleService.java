package org.application.lesson_15_springsecurity.service;

import org.application.lesson_15_springsecurity.model.Role;

public interface RoleService {

    Role findRoleByName(String name);

    void addRoleToUser(long userid, long roleId);
}
