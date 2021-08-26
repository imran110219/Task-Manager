package com.sadman.taskmanager.service;

import com.sadman.taskmanager.dto.ProjectDTO;
import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.iservice.ProjectService;
import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.Task;
import com.sadman.taskmanager.payload.response.MessageResponse;
import com.sadman.taskmanager.repository.ProjectRepository;
import com.sadman.taskmanager.repository.TaskRepository;
import com.sadman.taskmanager.repository.UserRepository;
import com.sadman.taskmanager.util.DataUtils;
import com.sadman.taskmanager.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sadman
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    DataUtils dataUtils;

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projectList = repository.findAll();
        return dataUtils.convertProjectDTOList(projectList);
    }

    @Override
    public List<ProjectDTO> getCurrentUserProjects() {
        int userId = dataUtils.getCurrentUserId();
        List<Project> projectList = repository.findAllByUserId(userId);
        return dataUtils.convertProjectDTOList(projectList);
    }

    @Override
    public List<ProjectDTO> getAllProjectsByUserId(int userId) {
        List<Project> projectList = repository.findAllByUserId(userId);
        return dataUtils.convertProjectDTOList(projectList);
    }

    @Override
    public Project getProjectById(int id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public ResponseEntity<?> createProject(ProjectDTO projectDTO) {
        int userId = dataUtils.getCurrentUserId();
        if (repository.findByNameAndUserId(projectDTO.getProjectName(), userId) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Duplicate Project Name!"));
        }
        Project project = new Project();
        project.setName(projectDTO.getProjectName());
        project.setUser(userRepository.getById(userId));
        Project savedProject = repository.save(project);
        return ResponseEntity
                .ok()
                .body(new MessageResponse(project.getName() + " is created by " + project.getUser().getFirstName() + " " + project.getUser().getLastName()));
    }

    @Override
    public Project updateProject(Project newProject, int id) {
        return repository.findById(id)
                .map(Project -> {
                    Project.setName(newProject.getName());
                    Project.setUser(newProject.getUser());
                    return repository.save(Project);
                })
                .orElseGet(() -> {
                    newProject.setId(id);
                    return repository.save(newProject);
                });
    }

    @Override
    public ResponseEntity<?> deleteProjectById(int id) {
        Project project = repository.getById(id);
        int userId = dataUtils.getCurrentUserId();
        if (project.getUser().getId() != userId) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: You are unauthorized!"));
        }
        if (taskRepository.findAllByProjectId(id).size() != 0) {
            return ResponseEntity.unprocessableEntity().body(new MessageResponse("Failed to delete the project"));
        }
        repository.deleteById(id);
        return ResponseEntity.ok().body(new MessageResponse("Project is Deleted Successfully"));
    }
}

