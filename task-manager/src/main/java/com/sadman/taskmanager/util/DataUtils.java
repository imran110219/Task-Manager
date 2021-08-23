package com.sadman.taskmanager.util;

import com.sadman.taskmanager.dto.ProjectDTO;
import com.sadman.taskmanager.dto.TaskDTO;
import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sadman
 */
public class DataUtils {
    public static List<ProjectDTO> convertProjectDTOList(List<Project> projectList){
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        projectList.forEach((project) -> {
            ProjectDTO projectDTO = new ProjectDTO(project.getName(), project.getUser().getUserName());
            projectDTOList.add(projectDTO);
        });
        return projectDTOList;
    }

    public static List<TaskDTO> convertTaskDTOList(List<Task> taskList){
        List<TaskDTO> taskDTOList = new ArrayList<>();
        taskList.forEach((task) -> {
            TaskDTO taskDTO = new TaskDTO(task.getName(), task.getProject().getName(), task.getUser().getUserName(), task.getStatus().toString(), task.getDescription(), task.getEndDate());
            taskDTOList.add(taskDTO);
        });
        return taskDTOList;
    }
}
