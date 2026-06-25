
package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDate;
import java.util.List;

import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequest {

	@NotBlank(message = "project name is required.")
	private String name;
	private String description;
	private ProjectStatus status = ProjectStatus.NEW;
	@NotBlank(message="start date is required.")
	private LocalDate startDate;
	private LocalDate endDate;
	private List<Long> TeamMemberIds;

}
