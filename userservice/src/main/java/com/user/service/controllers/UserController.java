package com.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.service.entites.Users;
import com.user.service.services.UserServicesImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServicesImpl userServicesImpl;

    // create user
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users savedUser = userServicesImpl.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    // get all user
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userServicesImpl.getAllUser();
        return new ResponseEntity<>(List.copyOf(users), HttpStatus.OK);
    }
    // get single user

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getSingleUser(@PathVariable String userId) {
        Users user = userServicesImpl.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // update single user
    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable String userId) {
        Users user1 = userServicesImpl.updateUserById(user, userId);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    // delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userServicesImpl.deleteUserById(userId);
        return new ResponseEntity<>("User is deleted successfully...", HttpStatus.OK);
    }
}
