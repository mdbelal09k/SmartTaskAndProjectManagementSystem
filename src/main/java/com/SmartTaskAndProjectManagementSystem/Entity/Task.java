package com.SmartTaskAndProjectManagementSystem.Entity;

import java.time.LocalDate;
import java.util.List;

import com.SmartTaskAndProjectManagementSystem.Enums.TaskPrioritys;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)

	private String title;
	@Column(nullable = false)

	@Enumerated(EnumType.STRING)
	private TaskStatus status = TaskStatus.TODO;
	private String description;

	@Enumerated(EnumType.STRING)
	private TaskPrioritys priority = TaskPrioritys.MEDIUMN;

	private LocalDate dueDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigned_member_id")
	private TeamMember assignedMember;

	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	private List<Comment> comments;

}
