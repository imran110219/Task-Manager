package com.sadman.taskmanager.iservice;

import com.sadman.taskmanager.dto.ProjectDTO;
import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.Task;

import java.util.List;

/**
 * @author Sadman
 */
public interface ProjectService {
    List<ProjectDTO> getAllProjects();
    List<ProjectDTO> getCurrentUserProjects();
    List<ProjectDTO> getAllProjectsByUserId(int userId);
    Project getProjectById(int id) throws RecordNotFoundException;
    Project createProject(Project project);
    Project updateProject(Project newProject, int id);
    void deleteProjectById(int id);
}
