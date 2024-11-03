package com.user.service.services;

import java.util.List;

import com.user.service.entites.Users;

public interface UserServices {
    // create user
    Users createUser(Users user);

    // get all users
    List<Users> getAllUser();

    // get user by Id

    Users getUserById(String id);

    // delete user

    void deleteUserById(String id);

    // Update User

    Users updateUserById(Users user, String id);
}
