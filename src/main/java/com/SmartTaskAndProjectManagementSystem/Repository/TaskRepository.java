package com.SmartTaskAndProjectManagementSystem.Repository;
//repository/TaskRepository.java

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SmartTaskAndProjectManagementSystem.Entity.Task;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskPrioritys;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long >,
     JpaSpecificationExecutor<Task> {

 Optional<Task> findByIdAndIsDeletedFalse(Long id);
 Page<Task> findAllByIsDeletedFalse(Pageable pageable);

 Page<Task> findByProjectIdAndIsDeletedFalse(Long projectId, Pageable pageable);
 Page<Task> findByAssignedMemberIdAndIsDeletedFalse(Long memberId, Pageable pageable);
 Page<Task> findByStatusAndIsDeletedFalse(TaskStatus status, Pageable pageable);
 Page<Task> findByPriorityAndIsDeletedFalse(TaskPrioritys priority, Pageable pageable);

 // Dynamic filter
 @Query("SELECT t FROM Task t WHERE t.isDeleted = false AND " +
        "(:projectId IS NULL OR t.project.id = :projectId) AND " +
        "(:memberId IS NULL OR t.assignedMember.id = :memberId) AND " +
        "(:status IS NULL OR t.status = :status) AND " +
        "(:priority IS NULL OR t.priority = :priority)")
 Page<Task> filterTasks(@Param("projectId") Long projectId,
                         @Param("memberId") Long memberId,
                         @Param("status") TaskStatus status,
                         @Param("priority") TaskPrioritys priority,
                         Pageable pageable);

 // Dashboard queries
 long countByIsDeletedFalse();
 long countByStatusAndIsDeletedFalse(TaskStatus status);

 @Query("SELECT COUNT(t) FROM Task t WHERE t.isDeleted = false AND " +
        "t.dueDate < :today AND t.status <> 'DONE'")
 long countOverdueTasks(@Param("today") LocalDate today);

 @Query("SELECT t.project.id, t.project.name, COUNT(t) FROM Task t " +
        "WHERE t.isDeleted = false GROUP BY t.project.id, t.project.name")
 List<Object[]> countTasksPerProject();

 // Report queries
 @Query("SELECT t FROM Task t WHERE t.isDeleted = false AND t.status = 'DONE' AND " +
        "t.updatedAt BETWEEN :startDate AND :endDate")
 List<Task> findCompletedTasksBetween(@Param("startDate") LocalDate startDate,
                                       @Param("endDate") LocalDate endDate);

 @Query("SELECT t FROM Task t WHERE t.isDeleted = false AND " +
        "t.project.id = :projectId AND t.status <> 'DONE'")
 List<Task> findPendingTasksByProject(@Param("projectId") Long projectId);

 @Query("SELECT t.assignedMember.id, t.assignedMember.name, COUNT(t) FROM Task t " +
        "WHERE t.isDeleted = false AND t.assignedMember IS NOT NULL " +
        "GROUP BY t.assignedMember.id, t.assignedMember.name")
 List<Object[]> getMemberWorkload();
}