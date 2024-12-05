package com.microservice.user.controller;

import com.microservice.user.dto.UserDTO;
import com.microservice.user.error.InvalidArgumentException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.model.UserEntity;
import com.microservice.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@RequestBody UserDTO userDTO) throws InvalidArgumentException {
        UserEntity user = userService.create(userDTO);
        return user;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws ResourceNotFoundException {
        UserEntity user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) throws ResourceNotFoundException, InvalidArgumentException{
        UserEntity updatedUser = userService.update(id, updatedUserDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ResourceNotFoundException{
        userService.delete(id);
    }
}
