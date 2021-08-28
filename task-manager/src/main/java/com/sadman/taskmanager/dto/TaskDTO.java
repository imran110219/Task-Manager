package com.sadman.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @author Sadman
 */
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String name;
    private int projectId;
    private String userName;
    private String status;
    private String description;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date endDate;
}
