package com.SmartTaskAndProjectManagementSystem.service.Impl;

import com.SmartTaskAndProjectManagementSystem.Entity.TeamMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.SmartTaskAndProjectManagementSystem.Entity.Comment;
import com.SmartTaskAndProjectManagementSystem.Entity.Task;
import com.SmartTaskAndProjectManagementSystem.Repository.CommentRepository;
import com.SmartTaskAndProjectManagementSystem.Repository.TaskRepository;
import com.SmartTaskAndProjectManagementSystem.Repository.TeamMemberRepository;
import com.SmartTaskAndProjectManagementSystem.dto.CommentRequest;
import com.SmartTaskAndProjectManagementSystem.dto.CommentResponse;
import com.SmartTaskAndProjectManagementSystem.exception.ResourceNotFoundException;
import com.SmartTaskAndProjectManagementSystem.mapper.CommentMapper;
import com.SmartTaskAndProjectManagementSystem.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final TeamMemberRepository memberRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponse create(CommentRequest request) {
        Task task = taskRepository.findByIdAndIsDeletedFalse(request.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task", request.getTaskId()));

        TeamMember member = memberRepository.findByIdAndIsDeletedFalse(request.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("TeamMember", request.getMemberId()));

        Comment comment = Comment.builder()
                .message(request.getMessage())
                .task(task)
                .member(member)
                .build();

        return commentMapper.toResponse(commentRepository.save(comment));
    }

    @Override
    public CommentResponse getById(Long id) {
        return commentMapper.toResponse(findById(id));
    }

    @Override
    public Page<CommentResponse> getByTask(Long taskId, Pageable pageable) {
        return commentRepository.findByTaskIdAndIsDeletedFalse(taskId, pageable)
                .map(commentMapper::toResponse);
    }

    @Override
    public Page<CommentResponse> getByMember(Long memberId, Pageable pageable) {
        return commentRepository.findByMemberIdAndIsDeletedFalse(memberId, pageable)
                .map(commentMapper::toResponse);
    }

    @Override
    public CommentResponse update(Long id, CommentRequest request) {
        Comment comment = findById(id);
        comment.setMessage(request.getMessage());
        return commentMapper.toResponse(commentRepository.save(comment));
    }

    @Override
    public void delete(Long id) {
        Comment comment = findById(id);
        comment.setIsDeleted(true);
        commentRepository.save(comment);
    }

    private Comment findById(Long id) {
        return commentRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", id));
    }
}
