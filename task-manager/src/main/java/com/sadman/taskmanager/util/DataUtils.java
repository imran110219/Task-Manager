package com.sadman.taskmanager.util;

import com.sadman.taskmanager.dto.ProjectDTO;
import com.sadman.taskmanager.dto.TaskDTO;
import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.Task;
import com.sadman.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sadman
 */
@Component
public class DataUtils {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    public List<ProjectDTO> convertProjectDTOList(List<Project> projectList){
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        projectList.forEach((project) -> {
            ProjectDTO projectDTO = new ProjectDTO(project.getName(), project.getUser().getUserName());
            projectDTOList.add(projectDTO);
        });
        return projectDTOList;
    }

    public List<TaskDTO> convertTaskDTOList(List<Task> taskList){
        List<TaskDTO> taskDTOList = new ArrayList<>();
        taskList.forEach((task) -> {
            TaskDTO taskDTO = new TaskDTO(task.getName(), task.getProject().getName(), task.getUser().getUserName(), task.getStatus().toString(), task.getDescription(), task.getEndDate());
            taskDTOList.add(taskDTO);
        });
        return taskDTOList;
    }

    public int getCurrentUserId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String userName = jwtUtils.getUserNameFromJwtToken(token);
        return userRepository.findByUserName(userName).getId();
    }
}
