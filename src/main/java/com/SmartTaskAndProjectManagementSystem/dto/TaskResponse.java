///-> Task ///->  dto/TaskResponse.java

package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.SmartTaskAndProjectManagementSystem.Enums.TaskPrioritys;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

	private int id;
	private String title;
	private String description;
	private TaskStatus status;
	private TaskPrioritys prooritys;
	private LocalDate dueDate;
	private Long projectId;
	private String projectName;
	private Long assignedMemberId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
