package com.sadman.taskmanager.iservice;

import com.sadman.taskmanager.dto.TaskDTO;
import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Sadman
 */
public interface TaskService {
    ResponseEntity<?> getAllTasks();
    List<TaskDTO> getCurrentUserTasks();
    ResponseEntity<?> getAllTasksByProjectId(int projectId);
    List<TaskDTO> getAllTasksByUserId(int userId);
    ResponseEntity<?> getAllTasksByStatus(String status);
    ResponseEntity<?> getAllExpiredTasks();
    List<TaskDTO> getAllExpiredTasksByStatus(String status);
    ResponseEntity<?> getTaskById(int id) throws RecordNotFoundException;
    ResponseEntity<?> createTask(TaskDTO taskDTO);
    ResponseEntity<?> updateTask(TaskDTO newTaskDTO, int id);
    void deleteTaskById(int id);
}
