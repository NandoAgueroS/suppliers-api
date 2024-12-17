package com.microservice.user.controller;

import com.microservice.user.dto.UserDTO;
import com.microservice.user.error.InvalidArgumentException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.http.response.UserResponse;
import com.microservice.user.model.UserEntity;
import com.microservice.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@RequestBody UserDTO userDTO) throws InvalidArgumentException {
        UserEntity user = userService.create(userDTO);
        return user;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findById(@PathVariable String username) throws ResourceNotFoundException {
        UserEntity user = userService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }
    @GetMapping("/{username}/details")
    public ResponseEntity<?> findDetailsById(@PathVariable String username) throws ResourceNotFoundException {
        UserResponse user = userService.getUserProfile(username);
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> update(@PathVariable String username, @RequestBody UserDTO updatedUserDTO) throws ResourceNotFoundException, InvalidArgumentException{
        UserEntity updatedUser = userService.update(username, updatedUserDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedUser);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String username) throws ResourceNotFoundException{
        userService.delete(username);
    }
}
