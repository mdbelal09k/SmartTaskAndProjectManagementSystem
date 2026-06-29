
package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDate;
import java.util.List;

import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;



import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectRequest {

	@NotNull(message = "project name is required.")
	private String name;
	private String description;
	private ProjectStatus status = ProjectStatus.NEW;
	@NotNull(message="start date is required.")
	private LocalDate startDate;
	private LocalDate endDate;
	private List<Long> TeamMemberIds;

}
