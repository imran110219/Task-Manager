package com.sadman.taskmanager.repository;

import com.sadman.taskmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String role);
}
