package com.microservice.user.service;

import com.microservice.user.dto.UserDTO;
import com.microservice.user.error.InvalidArgumentException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.model.RoleEntity;
import com.microservice.user.model.UserEntity;
import com.microservice.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Override
    public UserEntity create(UserDTO userDTO) throws InvalidArgumentException {
        UserEntity user = mapDtoToEntity(userDTO);
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("Failed to delete, user not found");
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity update(Long id, UserDTO updatedUserDTO) throws ResourceNotFoundException, InvalidArgumentException {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("Failed to update, user not found");
        UserEntity updatedUser = mapDtoToEntity(updatedUserDTO);
        updatedUser.setUserId(id);
        userRepository.save(updatedUser);
        return updatedUser;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long id) throws ResourceNotFoundException {
        Optional<UserEntity> user = userRepository.findById(id);
        if (!user.isPresent()) throw new ResourceNotFoundException("User not found");
        return user.get();
    }

    @Override
    public UserEntity mapDtoToEntity(UserDTO userDTO) throws InvalidArgumentException{
        Set<RoleEntity> roles = roleService.findRolesByNames(userDTO.getRolesName());
        UserEntity user = new UserEntity().builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles(roles)
                .build();
        return user;
    }
}
