///-> task ///-> // dto/TaskRequest.java

package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDate;

import com.SmartTaskAndProjectManagementSystem.Enums.TaskPrioritys;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequest {

	private String title;
	private String description;
	private TaskStatus status = TaskStatus.TODO;
	private TaskPrioritys prioritys = TaskPrioritys.MEDIUMN;
	private LocalDate dueDate;
	@NotNull(message="project ID is requird!")
	private Long ProjectId;
	private Long assignedMemberId;

}
