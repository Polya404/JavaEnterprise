package org.application.lesson_15_springsecurity.service;

import org.application.lesson_15_springsecurity.model.Role;

import java.util.List;

public interface RoleService {

    Role findRoleByName(String name);

    void addRoleToUser(long userid, long roleId);

    List<Role> getRoleByUserId(long userid);

    List<Role> getAllExistRoles();
}
