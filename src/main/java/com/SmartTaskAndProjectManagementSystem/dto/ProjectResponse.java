///-> project ///-> dto/ProjectRequest.java
package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<TeamMemberResponse> teamMembers;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}