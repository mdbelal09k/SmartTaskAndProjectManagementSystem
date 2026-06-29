package com.SmartTaskAndProjectManagementSystem.dto;

//dto/TaskCountPerProjectResponse.java

import lombok.*;

@Data @Builder
 @AllArgsConstructor @NoArgsConstructor
public class TaskCountPerProjectResponse {
 private Long projectId;
 private String projectName;
 private Long taskCount;
 
}