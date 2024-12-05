package com.microservice.user.service;

import com.microservice.user.error.DuplicateEntityException;
import com.microservice.user.error.InvalidArgumentException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.model.RoleEntity;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    void create(RoleEntity role) throws DuplicateEntityException;
    void update(String name, RoleEntity updatedRole) throws ResourceNotFoundException;
    RoleEntity findByName(String name) throws ResourceNotFoundException;
    List<RoleEntity> findAll();
    void delete(String name) throws ResourceNotFoundException;
    Set findRolesByNames(Set<String> rolesName) throws InvalidArgumentException;
}
