package com.SmartTaskAndProjectManagementSystem.service;

//service/TeamMemberService.java

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberRequest;
import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberResponse;

public interface TeamMemberService {
 TeamMemberResponse create(TeamMemberRequest request);
 TeamMemberResponse getById(Long id);
 Page<TeamMemberResponse> getAll(Pageable pageable);
 TeamMemberResponse update(Long id, TeamMemberRequest request);
 void delete(Long id);
 Page<TeamMemberResponse> search(String keyword, Pageable pageable);
 Page<TeamMemberResponse> getByProject(Long projectId, Pageable pageable);
}