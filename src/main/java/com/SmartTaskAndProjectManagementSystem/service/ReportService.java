package com.SmartTaskAndProjectManagementSystem.service;

//service/ReportService.java

import java.time.LocalDate;
import java.util.List;

import com.SmartTaskAndProjectManagementSystem.dto.TaskResponse;

public interface ReportService {
	List<TaskResponse> completedTasksBetween(LocalDate start, LocalDate end);

	List<TaskResponse> pendingTasksByProject(Long projectId);

	List<WorkloadResponse> memberWorkloadReport();
}