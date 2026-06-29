package com.SmartTaskAndProjectManagementSystem.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SmartTaskAndProjectManagementSystem.dto.CommentRequest;
import com.SmartTaskAndProjectManagementSystem.dto.CommentResponse;

public interface CommentService {

    CommentResponse create(CommentRequest request);

    CommentResponse getById(Long id);

    Page<CommentResponse> getByTask(Long taskId, Pageable pageable);

    Page<CommentResponse> getByMember(Long memberId, Pageable pageable);

    CommentResponse update(Long id, CommentRequest request);

    void delete(Long id);
}