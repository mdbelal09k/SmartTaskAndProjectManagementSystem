package com.SmartTaskAndProjectManagementSystem.Repository;
//repository/ProjectRepository.java


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SmartTaskAndProjectManagementSystem.Entity.Project;
import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;

import java.util.Optional;
public interface ProjectRepository extends JpaRepository<Project,Long>,
     JpaSpecificationExecutor<Project> {

 Optional<Project> findByIdAndIsDeletedFalse(Long id);
 Page<Project> findAllByIsDeletedFalse(Pageable pageable);

 // Search by name
 Page<Project> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name, Pageable pageable);

 // Filter by status
 Page<Project> findByStatusAndIsDeletedFalse(ProjectStatus status, Pageable pageable);

 // Dashboard
 long countByIsDeletedFalse();
 long countByStatusAndIsDeletedFalse(ProjectStatus status);

 @Query("SELECT p FROM Project p WHERE p.isDeleted = false AND " +
        "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
        "(:status IS NULL OR p.status = :status)")
 Page<Project> filterProjects(@Param("name") String name,
                               @Param("status") ProjectStatus status,
                               Pageable pageable);

 @Query("SELECT COUNT(t) FROM Task t WHERE t.project.id = :projectId AND t.isDeleted = false")
 Long countTasksByProjectId(@Param("projectId") Long projectId);
}
