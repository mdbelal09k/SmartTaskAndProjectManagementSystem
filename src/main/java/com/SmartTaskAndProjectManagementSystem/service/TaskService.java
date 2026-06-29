package com.SmartTaskAndProjectManagementSystem.service;

//service/TaskService.java

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SmartTaskAndProjectManagementSystem.Enums.TaskPrioritys;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;
import com.SmartTaskAndProjectManagementSystem.dto.TaskRequest;
import com.SmartTaskAndProjectManagementSystem.dto.TaskResponse;

public interface TaskService {
	TaskResponse create(TaskRequest request);

	TaskResponse getById(Long id);

	Page<TaskResponse> getAll(Pageable pageable);

	TaskResponse update(Long id, TaskRequest request);

	void delete(Long id);

	Page<TaskResponse> getByProject(Long projectId, Pageable pageable);

	Page<TaskResponse> getByMember(Long memberId, Pageable pageable);

	Page<TaskResponse> filter(Long projectId, Long memberId, TaskStatus status, TaskPrioritys priority,
			Pageable pageable);
}