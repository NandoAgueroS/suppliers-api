package com.microservice.user.service;

import com.microservice.user.dto.UserDTO;
import com.microservice.user.error.InvalidArgumentException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.http.response.UserResponse;
import com.microservice.user.model.UserEntity;

import java.util.List;

public interface IUserService {

    UserEntity create(UserDTO userDTO) throws InvalidArgumentException;
    void delete(String username) throws ResourceNotFoundException;
    UserEntity update(String username, UserDTO updatedUserDTO) throws ResourceNotFoundException, InvalidArgumentException;
    List<UserEntity> findAll();
    UserEntity findByUsername(String username) throws ResourceNotFoundException;
    UserEntity mapDtoToEntity(UserDTO userDTO) throws InvalidArgumentException;
    UserResponse getUserProfile(String username) throws ResourceNotFoundException;
}
