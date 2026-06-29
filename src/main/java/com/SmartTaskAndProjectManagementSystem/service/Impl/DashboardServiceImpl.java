package com.SmartTaskAndProjectManagementSystem.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;
import com.SmartTaskAndProjectManagementSystem.Repository.ProjectRepository;
import com.SmartTaskAndProjectManagementSystem.Repository.TaskRepository;
import com.SmartTaskAndProjectManagementSystem.dto.DashboardResponse;
import com.SmartTaskAndProjectManagementSystem.dto.TaskCountPerProjectResponse;
import com.SmartTaskAndProjectManagementSystem.service.DashboardService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public DashboardResponse getDashboard() {

        List<TaskCountPerProjectResponse> taskCounts =
                taskRepository.countTasksPerProject()
                        .stream()
                        .map(row -> TaskCountPerProjectResponse.builder()
                                .projectId(((Number) row[0]).longValue())
                                .projectName((String) row[1])
                                .taskCount(((Number) row[2]).longValue())
                                .build())
                        .toList();

        return DashboardResponse.builder()
                .totalProjects(projectRepository.countByIsDeletedFalse())
                .totalTasks(taskRepository.countByIsDeletedFalse())
                .completedTasks(
                        taskRepository.countByStatusAndIsDeletedFalse(TaskStatus.DONE))
                .pendingTasks(
                        taskRepository.countByStatusAndIsDeletedFalse(TaskStatus.TODO)
                                + taskRepository.countByStatusAndIsDeletedFalse(TaskStatus.IN_PROGRESS))
                .overdueTasks(
                        taskRepository.countOverdueTasks(LocalDate.now()))
                .taskCountPerProject(taskCounts)
                .build();
    }

	
}