package com.SmartTaskAndProjectManagementSystem.dto;

///-> dto/teammemberRequest.java

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Data
public class TeamMemberRequest {

	@NotBlank(message = "name is requride!")
	private String name;
	@NotBlank
	@Email(message = "invalid email!")
	private String email;

	private String department;
	private String designation;

}
