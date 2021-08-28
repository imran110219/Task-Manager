package com.sadman.taskmanager.controller;

import com.sadman.taskmanager.dto.ProjectDTO;
import com.sadman.taskmanager.dto.TaskDTO;
import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.iservice.TaskService;
import com.sadman.taskmanager.iservice.UserService;
import com.sadman.taskmanager.model.Task;
import com.sadman.taskmanager.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasks(Model model) {
        return service.getAllTasks();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/tasks/mine")
    public List<TaskDTO> getAllTasksMine(Model model) {
        return service.getCurrentUserTasks();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tasks/users/{id}")
    public List<TaskDTO> getAllTasksByUserId(@PathVariable(value = "id") int userId) {
        return service.getAllTasksByUserId(userId);
    }

    @GetMapping("projects/{id}/tasks")
    public List<TaskDTO> getAllTasksByProjectId(@PathVariable(value = "id") int projectId) {
        return service.getAllTasksByProjectId(projectId);
    }

    @GetMapping("/tasks/status/{status}")
    public List<TaskDTO> getAllTasksByStatus(@PathVariable(value = "status") String status) {
        return service.getAllTasksByStatus(status);
    }

    @GetMapping("/tasks/expired/status/{status}")
    public List<TaskDTO> getAllExpiredTasksByStatus(@PathVariable(value = "status") String status) {
        return service.getAllExpiredTasksByStatus(status);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") int taskId)
            throws RecordNotFoundException {
        Task Task = service.getTaskById(taskId);
        return ResponseEntity.ok().body(Task);
    }

    @PostMapping(value = "/tasks", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        return service.createTask(taskDTO);
    }

    @PutMapping("/tasks/edit/{id}")
    public ResponseEntity<?> editTaskById(@RequestBody TaskDTO newTaskDTO, @PathVariable(value = "id") int taskId) {
        return service.updateTask(newTaskDTO, taskId);
    }

    @DeleteMapping("/tasks/delete/{id}")
    public void deleteTaskById(@PathVariable(value = "id") int taskId){
        service.deleteTaskById(taskId);
    }
}
