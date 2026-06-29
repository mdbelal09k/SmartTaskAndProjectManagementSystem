package com.SmartTaskAndProjectManagementSystem.service.Impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.SmartTaskAndProjectManagementSystem.Entity.Project;
import com.SmartTaskAndProjectManagementSystem.Entity.Task;
import com.SmartTaskAndProjectManagementSystem.Entity.TeamMember;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskPrioritys;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;
import com.SmartTaskAndProjectManagementSystem.Repository.ProjectRepository;
import com.SmartTaskAndProjectManagementSystem.Repository.TaskRepository;
import com.SmartTaskAndProjectManagementSystem.Repository.TeamMemberRepository;
import com.SmartTaskAndProjectManagementSystem.dto.TaskRequest;
import com.SmartTaskAndProjectManagementSystem.dto.TaskResponse;
import com.SmartTaskAndProjectManagementSystem.exception.ResourceNotFoundException;

import com.SmartTaskAndProjectManagementSystem.mapper.TaskMapper;
import com.SmartTaskAndProjectManagementSystem.service.TaskService;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TeamMemberRepository memberRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse create(TaskRequest request) {

        Project project = projectRepository
                .findByIdAndIsDeletedFalse(request.getProjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project", request.getProjectId()));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPrioritys())
                .dueDate(request.getDueDate())
                .project(project) // Verify field name
                .build();

        if (request.getAssignedMemberId() != null) {

            TeamMember member = memberRepository
                    .findByIdAndIsDeletedFalse(request.getAssignedMemberId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "TeamMember",
                                    request.getAssignedMemberId()));

            task.setAssignedMember(member);
        }

        return taskMapper.toResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponse getById(Long id) {
        return taskMapper.toResponse(findById(id));
    }

    @Override
    public Page<TaskResponse> getAll(Pageable pageable) {
        return taskRepository.findAllByIsDeletedFalse(pageable)
                .map(taskMapper::toResponse);
    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {

        Task task = findById(id);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPrioritys());
        task.setDueDate(request.getDueDate());

        if (request.getProjectId() != null) {

            Project project = projectRepository
                    .findByIdAndIsDeletedFalse(request.getProjectId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Project",
                                    request.getProjectId()));

            task.setProject(project); // Verify field name
        }

        if (request.getAssignedMemberId() != null) {

            TeamMember member = memberRepository
                    .findByIdAndIsDeletedFalse(request.getAssignedMemberId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "TeamMember",
                                    request.getAssignedMemberId()));

            task.setAssignedMember(member);
        }

        return taskMapper.toResponse(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        Task task = findById(id);
        task.setIsDeleted(true);
        taskRepository.save(task);
    }

    @Override
    public Page<TaskResponse> getByProject(Long projectId, Pageable pageable) {
        return taskRepository.findByProjectIdAndIsDeletedFalse(projectId, pageable)
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> getByMember(Long memberId, Pageable pageable) {
        return taskRepository.findByAssignedMemberIdAndIsDeletedFalse(memberId, pageable)
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> filter(Long projectId,
                                     Long memberId,
                                     TaskStatus status,
                                     TaskPrioritys priority,
                                     Pageable pageable) {

        return taskRepository
                .filterTasks(projectId, memberId, status, priority, pageable)
                .map(taskMapper::toResponse);
    }

    private Task findById(Long id) {
        return taskRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task", id));
    }
}