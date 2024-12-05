package com.microservice.user.service;

import com.microservice.user.error.DuplicateEntityException;
import com.microservice.user.error.InvalidArgumentException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.model.RoleEntity;
import com.microservice.user.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public void create(RoleEntity role) throws DuplicateEntityException {
        if (roleRepository.existsById(role.getName())) throw new DuplicateEntityException("The role you are trying to save already exists");
        roleRepository.save(role);
    }

    @Override
    public void update(String name, RoleEntity updatedRole) throws ResourceNotFoundException {
        if (!roleRepository.existsById(name)) throw new ResourceNotFoundException("Failed to update, role not found");
        updatedRole.setName(name);
        roleRepository.save(updatedRole);
    }

    @Override
    public RoleEntity findByName(String name) throws ResourceNotFoundException{
        Optional<RoleEntity> role = roleRepository.findById(name);
        if (!role.isPresent()) throw new ResourceNotFoundException("Role not found");
        return role.get();
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(String name) throws ResourceNotFoundException{
        if (!roleRepository.existsById(name)) throw new ResourceNotFoundException("Failed to delete, role not found");
        roleRepository.deleteById(name);
    }

    @Override
    public Set<RoleEntity> findRolesByNames(Set<String> rolesName) throws InvalidArgumentException {
        Set<RoleEntity> existingRoles = new HashSet<>(roleRepository.findAllById(rolesName));
        if (existingRoles.size() != rolesName.size()) throw new InvalidArgumentException("One or more roles provided are invalid");
        return existingRoles;
    }
}
