package com.SmartTaskAndProjectManagementSystem.Entity;

import java.util.List;

import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberResponse.TeamMemberResponseBuilder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team_members")


public class TeamMember extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;
	
	private String  department;
	private String designation;
	
	
	@OneToMany(mappedBy="assignedMember",cascade=CascadeType.ALL)
	private List<Task> tasks;
	
	@ManyToMany(mappedBy="teamMembers")
	private java.util.List<Project> projects;


	

}
