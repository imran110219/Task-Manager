package com.sadman.taskmanager.service;

import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.iservice.ProjectService;
import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.Task;
import com.sadman.taskmanager.repository.ProjectRepository;
import com.sadman.taskmanager.repository.UserRepository;
import com.sadman.taskmanager.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
    JwtUtils jwtUtils;

    @Override
    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    @Override
    public List<Project> getCurrentUserProjects() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String userName = jwtUtils.getUserNameFromJwtToken(token);
        int userId = userRepository.findByUserName(userName).getId();
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<Project> getAllProjectsByUserId(int userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Project getProjectById(int id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public Project createProject(Project project) {
        return repository.save(project);
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
    public void deleteProjectById(int id) {
        repository.deleteById(id);
    }
}
