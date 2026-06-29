package com.SmartTaskAndProjectManagementSystem.Repository;

//repository/CommentRepository.java

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SmartTaskAndProjectManagementSystem.Entity.Comment;

import java.util.Optional;
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndIsDeletedFalse(Long id);

    Page<Comment> findByTaskIdAndIsDeletedFalse(Long taskId, Pageable pageable);

    Page<Comment> findByMemberIdAndIsDeletedFalse(Long memberId, Pageable pageable);
}