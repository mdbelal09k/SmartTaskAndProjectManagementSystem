///-> Comment ///-> dto/CommentResponse.java

package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentResponse {

	private int id;
	private String message;
	private Long taskId;
	private String taskTitle;
	private Long memeberId;
	private String memberName;
	private LocalDateTime createdAt;

}
