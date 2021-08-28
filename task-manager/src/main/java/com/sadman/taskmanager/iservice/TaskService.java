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
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getCurrentUserTasks();
    List<TaskDTO> getAllTasksByProjectId(int projectId);
    List<TaskDTO> getAllTasksByUserId(int userId);
    List<TaskDTO> getAllTasksByStatus(String status);
    List<TaskDTO> getAllExpiredTasksByStatus(String status);
    Task getTaskById(int id) throws RecordNotFoundException;
    ResponseEntity<?> createTask(TaskDTO taskDTO);
    ResponseEntity<?> updateTask(TaskDTO newTaskDTO, int id);
    void deleteTaskById(int id);
}
