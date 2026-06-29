package com.SmartTaskAndProjectManagementSystem.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.SmartTaskAndProjectManagementSystem.Util.ApiResonse;
import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberRequest;
import com.SmartTaskAndProjectManagementSystem.dto.TeamMemberResponse;
import com.SmartTaskAndProjectManagementSystem.service.TeamMemberService;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResonse<TeamMemberResponse>> create(
            @Valid @RequestBody TeamMemberRequest request) {
       TeamMemberResponse response =memberService.create(request);
    System.out.println("created member : "+response);
       return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResonse.success("Member created", memberService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResonse<TeamMemberResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResonse.success("Member fetched", memberService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResonse<Page<TeamMemberResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Pageable pageable = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return ResponseEntity.ok(ApiResonse.success("Members fetched", memberService.getAll(pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResonse<TeamMemberResponse>> update(
            @PathVariable Long id, @Valid @RequestBody TeamMemberRequest request) {
        return ResponseEntity.ok(ApiResonse.success("Member updated", memberService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResonse<Void>> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.ok(ApiResonse.success("Member deleted", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResonse<Page<TeamMemberResponse>>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResonse.success("Search results",
                memberService.search(keyword, pageable)));
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<ApiResonse<Page<TeamMemberResponse>>> getByProject(
            @PathVariable Long projectId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResonse.success("Members fetched",
                memberService.getByProject(projectId, pageable)));
    }
}
