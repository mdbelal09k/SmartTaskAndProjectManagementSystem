///-> dto/TeamMemberResponse.java
package com.SmartTaskAndProjectManagementSystem.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberResponse {

	private int id;
	private String name;
	private String email;
	private String department;
	private String designation;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
