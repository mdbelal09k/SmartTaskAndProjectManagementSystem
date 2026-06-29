package com.SmartTaskAndProjectManagementSystem.service.Impl;

import com.SmartTaskAndProjectManagementSystem.Entity.TeamMember;
import com.SmartTaskAndProjectManagementSystem.Repository.TeamMemberRepository;
import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberRequest;
import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberResponse;
import com.SmartTaskAndProjectManagementSystem.exception.DuplicateResourceException;
import com.SmartTaskAndProjectManagementSystem.exception.ResourceNotFoundException;
import com.SmartTaskAndProjectManagementSystem.mapper.TeamMemberMapper;
import com.SmartTaskAndProjectManagementSystem.service.TeamMemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository memberRepository;
    private final TeamMemberMapper memberMapper;

    @Override
    public TeamMemberResponse create(TeamMemberRequest request) {

        if (memberRepository.existsByEmailAndIsDeletedFalse(request.getEmail())) {
            throw new DuplicateResourceException(
                    "Email already exists: " + request.getEmail());
        }

        TeamMember member = memberMapper.toEntity(request);

        return memberMapper.toRespnse(
                memberRepository.save(member));
    }

    @Override
    public TeamMemberResponse getById(Long id) {
        return memberMapper.toRespnse(findById(id));
    }

    @Override
    public Page<TeamMemberResponse> getAll(Pageable pageable) {
        return memberRepository.findAllByIsDeletedFalse(pageable)
                .map(memberMapper::toRespnse);
    }

    @Override
    public TeamMemberResponse update(Long id, TeamMemberRequest request) {

        TeamMember member = findById(id);

        if (!member.getEmail().equals(request.getEmail())
                && memberRepository.existsByEmailAndIsDeletedFalse(request.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists: " + request.getEmail());
        }

        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setDepartment(request.getDepartment());
        member.setDesignation(request.getDesignation());

        return memberMapper.toRespnse(
                memberRepository.save(member));
    }

    @Override
    public void delete(Long id) {
        TeamMember member = findById(id);
        member.setIsDeleted(true);
        memberRepository.save(member);
    }

    @Override
    public Page<TeamMemberResponse> search(String keyword, Pageable pageable) {
        return memberRepository.searchMembers(keyword, pageable)
                .map(memberMapper::toRespnse);
    }

    @Override
    public Page<TeamMemberResponse> getByProject(Long projectId,
                                                 Pageable pageable) {
        return memberRepository.findByProjectId(projectId, pageable)
                .map(memberMapper::toRespnse);
    }

    private TeamMember findById(Long id) {
        return memberRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("TeamMember", id));
    }
}