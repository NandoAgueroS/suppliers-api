package com.microservice.user.service;

import com.microservice.user.client.SupplierClient;
import com.microservice.user.dto.SupplierDTO;
import com.microservice.user.dto.UserDTO;
import com.microservice.user.error.InvalidArgumentException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.http.response.UserResponse;
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

    @Autowired
    private SupplierClient supplierClient;

    @Override
    public UserEntity create(UserDTO userDTO) throws InvalidArgumentException {
        UserEntity user = mapDtoToEntity(userDTO);
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(String username) throws ResourceNotFoundException {
        if (!userRepository.existsById(username)) throw new ResourceNotFoundException("Failed to delete, user not found");
        userRepository.deleteById(username);
    }

    @Override
    public UserEntity update(String username, UserDTO updatedUserDTO) throws ResourceNotFoundException, InvalidArgumentException {
        if (!userRepository.existsById(username)) throw new ResourceNotFoundException("Failed to update, user not found");
        UserEntity updatedUser = mapDtoToEntity(updatedUserDTO);
        updatedUser.setUsername(username);
        userRepository.save(updatedUser);
        return updatedUser;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findByUsername(String username) throws ResourceNotFoundException {
        Optional<UserEntity> user = userRepository.findById(username);
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

    @Override
    public UserResponse getUserProfile(String username) throws ResourceNotFoundException{
        Optional<UserEntity> userOptional = userRepository.findById(username);
        if (!userOptional.isPresent()) throw new ResourceNotFoundException("User Not Found");
        SupplierDTO supplierDTO = supplierClient.findByUsername(userOptional.get().getUsername());
        return UserResponse.builder()
                .user(userOptional.get())
                .supplier(supplierDTO)
                .build();
    }
}
