package com.sadman.taskmanager.service;

import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.iservice. UserService;
import com.sadman.taskmanager.model. User;
import com.sadman.taskmanager.repository. UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
     UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public  User getUserById(int id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public User getUserByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public User createUser( User task) {
        return repository.save(task);
    }

    @Override
    public User updateUser(User newUser, int id) {
        return repository.findById(id)
                .map( User -> {
                    User.setFirstName(newUser.getFirstName());
                    User.setLastName(newUser.getLastName());
                    User.setUserName(newUser.getUserName());
                    User.setEmail(newUser.getEmail());
                    User.setPassword(newUser.getPassword());
                    return repository.save( User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @Override
    public void deleteUserById(int id) {
        repository.deleteById(id);
    }
}
