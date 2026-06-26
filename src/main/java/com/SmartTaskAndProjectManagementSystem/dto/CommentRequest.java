///-> Comment ///-> dto/CommentRequest.java
package com.SmartTaskAndProjectManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequest {

	@NotBlank(message = "message is required!")
	private String message;

	@NotBlank(message = "taskId is requred!")
	private Long taskId;

	@NotBlank(message = "member Id is required")
	private Long memberId;
}
