///-> Task ///->  dto/TaskResponse.java

package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.SmartTaskAndProjectManagementSystem.Enums.TaskPrioritys;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_response")
public class TaskResponse {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;

	private TaskStatus status;
	private TaskPrioritys propritys;

	private LocalDate dueDate;

	private Long projectId;
	private String projectName;

	private Long assignedMemberId;
	private String assignedMemberName;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}