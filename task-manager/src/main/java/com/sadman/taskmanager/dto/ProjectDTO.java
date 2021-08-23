package com.sadman.taskmanager.dto;

import lombok.*;

/**
 * @author Sadman
 */
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private String projectName;
    private String userName;
}
