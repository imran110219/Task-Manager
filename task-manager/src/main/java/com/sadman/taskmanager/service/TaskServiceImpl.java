package com.sadman.taskmanager.service;

import com.sadman.taskmanager.dto.ProjectDTO;
import com.sadman.taskmanager.dto.TaskDTO;
import com.sadman.taskmanager.exception.RecordNotFoundException;
import com.sadman.taskmanager.iservice.TaskService;
import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.Status;
import com.sadman.taskmanager.model.Task;
import com.sadman.taskmanager.model.User;
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
import java.util.Optional;

/**
 * @author Sadman
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository repository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    DataUtils dataUtils;

    @Override
    public ResponseEntity<?> getAllTasks() {
        int userId = dataUtils.getCurrentUserId();
        User user = userRepository.getById(userId);
        if(user.getRole().getName().equals("ADMIN")){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: You are unauthorized!"));
        }
        List<Task> taskList = repository.findAll();
        return ResponseEntity.ok().body(dataUtils.convertTaskDTOList(taskList));
    }

    @Override
    public List<TaskDTO> getCurrentUserTasks() {
        int userId = dataUtils.getCurrentUserId();
        List<Task> taskList = repository.findAllByUserId(userId);
        return dataUtils.convertTaskDTOList(taskList);
    }

    @Override
    public ResponseEntity<?> getAllTasksByProjectId(int projectId) {
        int projectUserId = projectRepository.getById(projectId).getUser().getId();
        int userId = dataUtils.getCurrentUserId();
        if(projectUserId != userId){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: You are unauthorized!"));
        }
        List<Task> taskList = repository.findAllByProjectId(projectId);
        List<TaskDTO> taskDTOList = dataUtils.convertTaskDTOList(taskList);
        return ResponseEntity.ok().body(taskDTOList);
    }

    @Override
    public List<TaskDTO> getAllTasksByUserId(int userId) {
        List<Task> taskList = repository.findAllByUserId(userId);
        return dataUtils.convertTaskDTOList(taskList);
    }

    @Override
    public ResponseEntity<?> getAllTasksByStatus(String status) {
        int userId = dataUtils.getCurrentUserId();
        User user = userRepository.getById(userId);
        switch (status){
            case "open" : return ResponseEntity.ok().body(dataUtils.convertTaskDTOList(repository.findAllByStatusAndUser(Status.OPEN, user)));
            case "inprogress" : return ResponseEntity.ok().body(dataUtils.convertTaskDTOList(repository.findAllByStatusAndUser(Status.INPROGRESS, user)));
            case "closed" : return ResponseEntity.ok().body(dataUtils.convertTaskDTOList(repository.findAllByStatusAndUser(Status.CLOSED, user)));
            default: return null;
        }
    }

    @Override
    public ResponseEntity<?> getAllExpiredTasks() {
        int userId = dataUtils.getCurrentUserId();
        List<Task> allExpiredTasks = repository.findExpiredTasks(userId);
        List<TaskDTO> allExpiredTaskDTOList = dataUtils.convertTaskDTOList(allExpiredTasks);
        return ResponseEntity.ok().body(allExpiredTaskDTOList);
    }

    @Override
    public List<TaskDTO> getAllExpiredTasksByStatus(String status) {
        int userId = dataUtils.getCurrentUserId();
        List<Task> allExpiredTasks = repository.findExpiredTasks(userId);
        List<Task> finalTasks = new ArrayList<>();
        if(status.equals("open")){
            for (Task task : allExpiredTasks) {
                if(task.getStatus().equals(Status.OPEN)){
                    finalTasks.add(task);
                }
            }
        }
        else if(status.equals("inprogress")){
            for (Task task : allExpiredTasks) {
                if(task.getStatus().equals(Status.INPROGRESS)){
                    finalTasks.add(task);
                }
            }
        }
        else {
            for (Task task : allExpiredTasks) {
                if(task.getStatus().equals(Status.CLOSED)){
                    finalTasks.add(task);
                }
            }
        }
        return dataUtils.convertTaskDTOList(finalTasks);
    }

    @Override
    public ResponseEntity<?> getTaskById(int id) throws RecordNotFoundException {
        Task task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        int userId = dataUtils.getCurrentUserId();
        if(userId != task.getUser().getId()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: You are unauthorized!"));
        }
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setProjectId(task.getProject().getId());
        taskDTO.setUserName(userRepository.getById(userId).getUserName());
        taskDTO.setStatus(task.getStatus().toString());
        taskDTO.setEndDate(task.getEndDate());
        return ResponseEntity.ok().body(taskDTO);
    }

    @Override
    public ResponseEntity<?> createTask(TaskDTO taskDTO) {
        int userId = dataUtils.getCurrentUserId();
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setProject(projectRepository.getById(taskDTO.getProjectId()));
        task.setUser(userRepository.getById(userId));
        task.setStatus(Status.OPEN);
        task.setDescription(taskDTO.getDescription());
        Task savedTask = repository.save(task);
        return ResponseEntity
                .ok()
                .body(new MessageResponse(task.getName() + " is created by " + task.getUser().getFirstName() + " " + task.getUser().getLastName()));
    }

    @Override
    public ResponseEntity<?> updateTask(TaskDTO newTaskDTO, int id) {
        Optional<Task> tempTask = repository.findById(id);

        if (tempTask.isPresent()) {
            Task task = tempTask.get();
            if(task.getStatus().equals(Status.CLOSED)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            task.setStatus(dataUtils.stringToStatus(newTaskDTO.getStatus()));
            task.setDescription(newTaskDTO.getDescription());
            task.setEndDate(newTaskDTO.getEndDate());
            Task editedTask = repository.save(task);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(task.getName() + " is edited"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Task is not found"));
        }
    }

    @Override
    public void deleteTaskById(int id) {
        repository.deleteById(id);
    }
}
