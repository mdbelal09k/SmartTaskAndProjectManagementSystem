package com.SmartTaskAndProjectManagementSystem.Entity;

import java.time.LocalDate;

import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Project extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	private ProjectStatus status= ProjectStatus.NEW;

	private LocalDate startDate;
	private LocalDate endDate;
	

}
