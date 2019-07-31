package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);

    Role findByName(String name);
}