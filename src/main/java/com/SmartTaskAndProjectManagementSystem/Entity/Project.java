package com.SmartTaskAndProjectManagementSystem.Entity;

import java.time.LocalDate;
import java.util.List;

import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Project extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	private ProjectStatus status= ProjectStatus.NEW;

	private LocalDate startDate;
	private LocalDate endDate;
	 @ManyToMany
	    @JoinTable(
	            name = "project_team_members",
	            joinColumns = @JoinColumn(name = "project_id"),
	            inverseJoinColumns = @JoinColumn(name = "member_id")
	    )
	    private List<TeamMember> teamMembers;
	
	

	
	
	
	

}
