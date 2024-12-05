package com.microservice.user.controller;

import com.microservice.user.error.DuplicateEntityException;
import com.microservice.user.error.ResourceNotFoundException;
import com.microservice.user.model.RoleEntity;
import com.microservice.user.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<RoleEntity> roles = roleService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(roles);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) throws ResourceNotFoundException {
        RoleEntity role = roleService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(role);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleEntity save(@RequestBody RoleEntity role) throws DuplicateEntityException {
        roleService.create(role);
        return role;
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String name) throws ResourceNotFoundException {
        roleService.delete(name);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<?> update(@PathVariable String name, @RequestBody RoleEntity updatedRole) throws ResourceNotFoundException{
        roleService.update(name, updatedRole);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedRole);
    }
}
