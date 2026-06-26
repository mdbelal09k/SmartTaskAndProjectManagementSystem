///->TeamMemberMapper ka main kaam hai DTO aur Entity ke beech data convert karna.
///-> Mepper ////->// mapper/TeamMemberMapper.java
package com.SmartTaskAndProjectManagementSystem.mapper;

import org.springframework.stereotype.Component;

import com.SmartTaskAndProjectManagementSystem.Entity.TeamMember;

import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberRequest;
import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberResponse;
import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberResponse.TeamMemberResponseBuilder;

@Component
public class TeamMemberMapper {

	public TeamMember toEntity(TeamMemberRequest req) {
		return TeamMember.builder()
				.name(req.getName())
				.email(req.getEmail())
				.department(req.getDepartment())
				.designation(req.getDesignation())
				.build();		
	}

	public TeamMemberResponse toRespnse(TeamMember m) {
		return TeamMemberResponse.builder().id(m.getId()).name(m.getName()).email(m.getEmail())
				.department(m.getDepartment()).designation(m.getDesignation()).createdAt(m.getCreatedAt())
				.updatedAt(m.getUpdatedAt()).build();
	}

}
