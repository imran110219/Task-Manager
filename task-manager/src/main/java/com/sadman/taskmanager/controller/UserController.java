package com.sadman.taskmanager.controller;

import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.iservice.UserService;
import com.sadman.taskmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/users")
    public List<User> getAllUseres(Model model) {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId)
            throws RecordNotFoundException {
        User User = service.getUserById(userId);
        return ResponseEntity.ok().body(User);
    }

    @PostMapping(value = "/users", consumes = MediaType.ALL_VALUE)
    public User createUser(@Valid @RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping("/users/edit/{id}")
    public User editUserById(@RequestBody User newUser, @PathVariable(value = "id") int userId) {
        return service.updateUser(newUser, userId);
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUserById(@PathVariable(value = "id") int userId){
        service.deleteUserById(userId);
    }
}
