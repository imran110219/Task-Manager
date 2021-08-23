package com.sadman.taskmanager.iservice;

import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.model.Task;

import java.util.List;

/**
 * @author Sadman
 */
public interface TaskService {
    List<Task> getAllTasks();
    List<Task> getCurrentUserTasks();
    List<Task> getAllTasksByProjectId(int projectId);
    List<Task> getAllTasksByUserId(int userId);
    List<Task> getAllTasksByStatus(String status);
    List<Task> getAllExpiredTasksByStatus(String status);
    Task getTaskById(int id) throws RecordNotFoundException;
    Task createTask(Task Task);
    Task updateTask(Task newTask, int id);
    void deleteTaskById(int id);
}
