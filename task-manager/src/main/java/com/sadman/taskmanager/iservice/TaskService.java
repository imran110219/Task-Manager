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
    Task createTask(Task Task);
    ResponseEntity<Task> updateTask(Task newTask, int id);
    void deleteTaskById(int id);
}
