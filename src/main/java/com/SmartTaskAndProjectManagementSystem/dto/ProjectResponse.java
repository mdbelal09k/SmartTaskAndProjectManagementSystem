///-> project ///-> dto/ProjectRequest.java
package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;

import lombok.Data;

@Data
public class ProjectResponse {

	private int id;
	private String name;
	private String description;
	private ProjectStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<TeamMemberResponse> teamMembers;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAr;

}
