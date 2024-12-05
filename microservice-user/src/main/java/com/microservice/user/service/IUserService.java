package com.microservice.user.service;

import com.microservice.user.dto.UserDTO;
import com.microservice.user.error.InvalidArgumentException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.model.UserEntity;

import java.util.List;

public interface IUserService {

    UserEntity create(UserDTO userDTO) throws InvalidArgumentException;
    void delete(Long id) throws ResourceNotFoundException;
    UserEntity update(Long id, UserDTO updatedUserDTO) throws ResourceNotFoundException, InvalidArgumentException;
    List<UserEntity> findAll();
    UserEntity findById(Long id) throws ResourceNotFoundException;
    UserEntity mapDtoToEntity(UserDTO userDTO) throws InvalidArgumentException;
}
