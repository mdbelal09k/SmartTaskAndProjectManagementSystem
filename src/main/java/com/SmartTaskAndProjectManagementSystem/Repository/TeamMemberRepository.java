package com.SmartTaskAndProjectManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SmartTaskAndProjectManagementSystem.Entity.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>, JpaSpecificationExecutor<TeamMember> {
	Optional<TeamMember> findByEmailAndIsDeletedFalse(String email);

	boolean existsByEmailAndIsDeletedFalse(String email);

	Page<TeamMember> findAllByIsDeletedFalse(org.springframework.data.domain.Pageable pageable);

	@Query("SELECT m FROM TeamMember m WHERE m.isDeleted = false AND "
			+ "(LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(m.department) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	Page<TeamMember> searchMembers(String keyword, Pageable pageable);

	@Query("SELECT m FROM TeamMember m JOIN m.projects p WHERE p.id = :projectId AND m.isDeleted = false")
	Page<TeamMember> findByProjectId(Long projectId, Pageable pageable);


	Optional<TeamMember> findByIdAndIsDeletedFalse(Long id);
}
