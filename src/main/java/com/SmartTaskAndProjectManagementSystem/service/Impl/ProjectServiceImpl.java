package com.SmartTaskAndProjectManagementSystem.service.Impl;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.SmartTaskAndProjectManagementSystem.Entity.Project;
import com.SmartTaskAndProjectManagementSystem.Entity.TeamMember;
import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;
import com.SmartTaskAndProjectManagementSystem.Repository.ProjectRepository;
import com.SmartTaskAndProjectManagementSystem.Repository.TeamMemberRepository;
import com.SmartTaskAndProjectManagementSystem.dto.ProjectRequest;
import com.SmartTaskAndProjectManagementSystem.dto.ProjectResponse;
import com.SmartTaskAndProjectManagementSystem.mapper.ProjectMapper;
import com.SmartTaskAndProjectManagementSystem.service.ProjectService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TeamMemberRepository memberRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectResponse create(ProjectRequest request) {
        Project project = projectMapper.toEntity(request);

        if (request.getTeamMemberIds() != null && !request.getTeamMemberIds().isEmpty()) {
            List<TeamMember> members = memberRepository.findAllById(request.getTeamMemberIds());
            project.setTeamMembers(members);
        }

        return projectMapper.toResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponse getById(Long id) {
        return projectMapper.toResponse(findById(id));
    }

    @Override
    public Page<ProjectResponse> getAll(Pageable pageable) {
        return projectRepository.findAllByIsDeletedFalse(pageable)
                .map(projectMapper::toResponse);
    }

    @Override
    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = findById(id);
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setStatus(request.getStatus());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());

        if (request.getTeamMemberIds() != null) {
            List<TeamMember> members = memberRepository.findAllById(request.getTeamMemberIds());
            project.setTeamMembers(members);
        }

        return projectMapper.toResponse(projectRepository.save(project));
    }

    @Override
    public void delete(Long id) {
        Project project = findById(id);
        project.setIsDeleted(true);
        projectRepository.save(project);
    }

    @Override
    public Page<ProjectResponse> searchByName(String name, Pageable pageable) {
        return projectRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(name, pageable)
                .map(projectMapper::toResponse);
    }

    @Override
    public Page<ProjectResponse> filter(String name, ProjectStatus status, Pageable pageable) {
        return projectRepository.filterProjects(name, status, pageable)
                .map(projectMapper::toResponse);
    }

    @Override
    public ProjectResponse addMembers(Long projectId, List<Long> memberIds) {
        Project project = findById(projectId);
        List<TeamMember> newMembers = memberRepository.findAllById(memberIds);
        project.getTeamMembers().addAll(newMembers);
        return projectMapper.toResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponse removeMembers(Long projectId, List<Long> memberIds) {
        Project project = findById(projectId);
        project.getTeamMembers().removeIf(m -> memberIds.contains(m.getId()));
        return projectMapper.toResponse(projectRepository.save(project));
    }

    private Project findById(Long id) {
        return projectRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow();
    }
}