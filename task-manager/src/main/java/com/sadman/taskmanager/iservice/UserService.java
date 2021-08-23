package com.sadman.taskmanager.iservice;

import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.model.User;

import java.util.List;

/**
 * @author Sadman
 */
public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id) throws RecordNotFoundException;
    User getUserByUserName(String userName);
    User createUser(User user);
    User updateUser(User newUser, int id);
    void deleteUserById(int id);
}
