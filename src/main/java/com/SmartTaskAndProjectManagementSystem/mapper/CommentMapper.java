package com.SmartTaskAndProjectManagementSystem.mapper;

import org.springframework.stereotype.Component;

import com.SmartTaskAndProjectManagementSystem.Entity.Comment;
import com.SmartTaskAndProjectManagementSystem.dto.CommentResponse;

@Component
public class CommentMapper {

    public CommentResponse toResponse(Comment c) {

        return CommentResponse.builder()
                .id(c.getId())
                .message(c.getMessage())

                .taskId(
                        c.getTask() != null
                                ? Long.valueOf(c.getTask().getId())
                                : null)

                .taskTitle(
                        c.getTask() != null
                                ? c.getTask().getTitle()
                                : null)

                .memberId(
                        c.getMember() != null
                                ? Long.valueOf(c.getMember().getId())
                                : null)

                .memberName(
                        c.getMember() != null
                                ? c.getMember().getName()
                                : null)

                .createdAt(c.getCreatedAt())
                .build();
    }
}