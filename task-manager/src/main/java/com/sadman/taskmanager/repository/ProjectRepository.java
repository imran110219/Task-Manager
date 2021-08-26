package com.sadman.taskmanager.repository;

import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sadman
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByUserId(int userId);
    Project findByNameAndUserId(String name, int userId);
}
