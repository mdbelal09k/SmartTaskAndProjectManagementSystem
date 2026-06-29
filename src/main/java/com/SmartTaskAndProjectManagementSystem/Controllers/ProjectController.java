package com.SmartTaskAndProjectManagementSystem.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.SmartTaskAndProjectManagementSystem.Enums.ProjectStatus;
import com.SmartTaskAndProjectManagementSystem.Util.ApiResonse;
import com.SmartTaskAndProjectManagementSystem.dto.ProjectRequest;
import com.SmartTaskAndProjectManagementSystem.dto.ProjectResponse;
import com.SmartTaskAndProjectManagementSystem.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResonse<ProjectResponse>> create(
            @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResonse.success("Project created", projectService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResonse<ProjectResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResonse.success("Project fetched", projectService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResonse<Page<ProjectResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Pageable pageable = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return ResponseEntity.ok(ApiResonse.success("Projects fetched", projectService.getAll(pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResonse<ProjectResponse>> update(
            @PathVariable Long id, @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(ApiResonse.success("Project updated", projectService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResonse<Void>> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok(ApiResonse.success("Project deleted", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResonse<Page<ProjectResponse>>> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResonse.success("Search results",
                projectService.searchByName(name, pageable)));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResonse<Page<ProjectResponse>>> filter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ProjectStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResonse.success("Filtered projects",
                projectService.filter(name, status, pageable)));
    }

    @PostMapping("/{projectId}/members/add")
    public ResponseEntity<ApiResonse<ProjectResponse>> addMembers(
            @PathVariable Long projectId, @RequestBody List<Long> memberIds) {
        return ResponseEntity.ok(ApiResonse.success("Members added",
                projectService.addMembers(projectId, memberIds)));
    }

    @PostMapping("/{projectId}/members/remove")
    public ResponseEntity<ApiResonse<ProjectResponse>> removeMembers(
            @PathVariable Long projectId, @RequestBody List<Long> memberIds) {
        return ResponseEntity.ok(ApiResonse.success("Members removed",
                projectService.removeMembers(projectId, memberIds)));
    }
}