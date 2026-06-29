package com.SmartTaskAndProjectManagementSystem.service;

//service/ProjectService.java

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;
import com.SmartTaskAndProjectManagementSystem.dto.ProjectRequest;
import com.SmartTaskAndProjectManagementSystem.dto.ProjectResponse;

public interface ProjectService {
 ProjectResponse create(ProjectRequest request);
 ProjectResponse getById(Long id);
 Page<ProjectResponse> getAll(Pageable pageable);
 ProjectResponse update(Long id, ProjectRequest request);
 void delete(Long id);
 Page<ProjectResponse> searchByName(String name, Pageable pageable);
 Page<ProjectResponse> filter(String name, ProjectStatus status, Pageable pageable);
 ProjectResponse addMembers(Long projectId, java.util.List<Long> memberIds);
 ProjectResponse removeMembers(Long projectId, java.util.List<Long> memberIds);
}