package com.thinkon.userManager.controller;

import com.thinkon.userManager.model.User;
import com.thinkon.userManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")

public class UserController{
    @Autowired
    private UserService userService;

    //Creating a new user
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //Getting an user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    //Getting all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Updating an user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedInfo){
        User updatedUser = userService.updateUser(id, updatedInfo);
        return ResponseEntity.ok(updatedUser);
    }

    //Deleting a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}