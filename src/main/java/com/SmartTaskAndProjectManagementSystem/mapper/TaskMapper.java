///-> mapper ///-> // mapper/CommentMapper.java
package com.SmartTaskAndProjectManagementSystem.mapper;

import org.springframework.stereotype.Component;

import com.SmartTaskAndProjectManagementSystem.Entity.Comment;
import com.SmartTaskAndProjectManagementSystem.dto.CommentResponse;

@Component
public class TaskMapper {
	public CommentResponse toResponse(Comment c) {
		
		return CommentResponse.Builder()
				.getId(c.getId())
				.message(c.getMessage())
				.taskId(c.getTask()!=null ? null c.getTask())
	}

}
