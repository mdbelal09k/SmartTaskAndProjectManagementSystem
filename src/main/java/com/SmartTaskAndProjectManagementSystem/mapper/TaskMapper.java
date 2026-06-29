///-> mapper ///-> // mapper/CommentMapper.java
package com.SmartTaskAndProjectManagementSystem.mapper;

import org.springframework.stereotype.Component;


import com.SmartTaskAndProjectManagementSystem.Entity.Task;
import com.SmartTaskAndProjectManagementSystem.dto.TaskResponse;

@Component
public class TaskMapper {

    public TaskResponse toResponse(Task t) {

        return TaskResponse.builder()
                .id(t.getId())
                .title(t.getTitle())
                .description(t.getDescription())
                .status(t.getStatus())
                .propritys(t.getPriority())
                .dueDate(t.getDueDate())

                .projectId(
                        t.getProject() != null
                                ? (long) t.getProject().getId()
                                : null)

                .projectName(
                        t.getProject() != null
                                ? t.getProject().getName()
                                : null)

                .assignedMemberId(
                        t.getAssignedMember() != null
                                ? (long) t.getAssignedMember().getId()
                                : null)

                .assignedMemberName(
                        t.getAssignedMember() != null
                                ? t.getAssignedMember().getName()
                                : null)

                .createdAt(t.getCreatedAt())
                .updatedAt(t.getUpdatedAt())
                .build();
    }
}