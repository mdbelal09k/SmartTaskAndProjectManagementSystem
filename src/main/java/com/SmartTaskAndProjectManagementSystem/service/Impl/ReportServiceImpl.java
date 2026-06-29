package com.SmartTaskAndProjectManagementSystem.service.Impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.SmartTaskAndProjectManagementSystem.Repository.TaskRepository;
import com.SmartTaskAndProjectManagementSystem.dto.TaskResponse;
import com.SmartTaskAndProjectManagementSystem.mapper.TaskMapper;
import com.SmartTaskAndProjectManagementSystem.service.ReportService;
import com.SmartTaskAndProjectManagementSystem.service.WorkloadResponse;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponse> completedTasksBetween(LocalDate start, LocalDate end) {
        return taskRepository.findCompletedTasksBetween(start, end)
                .stream().map(taskMapper::toResponse).toList();
    }

    @Override
    public List<TaskResponse> pendingTasksByProject(Long projectId) {
        return taskRepository.findPendingTasksByProject(projectId)
                .stream().map(taskMapper::toResponse).toList();
    }

    @Override
    public List<WorkloadResponse> memberWorkloadReport() {
        return taskRepository.getMemberWorkload().stream()
                .map(row -> WorkloadResponse.builder()
                        .memberId((Long) row[0])
                        .memberName((String) row[1])
                        .taskCount((Long) row[2])
                        .build())
                .toList();
    }
}
