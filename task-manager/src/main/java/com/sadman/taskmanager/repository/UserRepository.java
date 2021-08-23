package com.sadman.taskmanager.repository;

import com.sadman.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sadman
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByUserName(String username);
}
