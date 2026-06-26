///-. Mapper /// mapper/ProjectMapper.java

/*Ye ProjectMapper bhi bilkul TeamMemberMapper ki tarah kaam karta hai. Iska kaam hai:

ProjectRequest DTO → Project Entity
Project Entity → ProjectResponse DTO*/

package com.SmartTaskAndProjectManagementSystem.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.SmartTaskAndProjectManagementSystem.Entity.Project;
import com.SmartTaskAndProjectManagementSystem.dto.ProjectRequest;
import com.SmartTaskAndProjectManagementSystem.dto.ProjectResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProjectMapper {
	private final TeamMemberMapper memberMapper;

	public Project toEntity(ProjectRequest req) {
		return Project.builder().name(req.getName()).description(req.getDescription()).status(req.getStatus())
				.startDate(req.getStartDate()).endDate(req.getEndDate()).build();
	}

	public ProjectResponse toResponse(Project p) {
		return ProjectResponse.builder().id(p.getId()).name(p.getName()).description(p.getDescription())
				.status(p.getStatus()).startDate(p.getStartDate()).endDate(p.getEndDate()).createdAt(p.getCreatedAt())
				.updatedAt(p.getUpdatedAt())
				.teamMembers(p.getTeamMember() == null ? null
						: p.getTeamMembers().stream().map(memberMapper::toResponse).collect(Collectors.toList()))
				.build();
	}
}
