package com.sadman.taskmanager.repository;

import com.sadman.taskmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sadman
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByUserId(int userId);
}
