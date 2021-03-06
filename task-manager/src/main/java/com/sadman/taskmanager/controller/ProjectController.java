package com.sadman.taskmanager.controller;

import com.sadman.taskmanager.dto.ProjectDTO;
import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.iservice.ProjectService;
import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    ProjectService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/projects")
    public ResponseEntity<?> getAllProjects(Model model) {
        return service.getAllProjects();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/projects/mine")
    public List<ProjectDTO> getAllProjectsMine(Model model) {
        return service.getCurrentUserProjects();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/projects/users/{id}")
    public List<ProjectDTO> getAllProjectsByUserId(@PathVariable(value = "id") int userId) {
        return service.getAllProjectsByUserId(userId);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") int projectId)
            throws RecordNotFoundException {
        Project Project = service.getProjectById(projectId);
        return ResponseEntity.ok().body(Project);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping(value = "/projects", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        return service.createProject(projectDTO);
    }

    @PutMapping("/projects/edit/{id}")
    public Project editProjectById(@RequestBody Project newProject, @PathVariable(value = "id") int projectId) {
        return service.updateProject(newProject, projectId);
    }

    @DeleteMapping("/projects/delete/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable(value = "id") int projectId){
        return service.deleteProjectById(projectId);
    }
}
