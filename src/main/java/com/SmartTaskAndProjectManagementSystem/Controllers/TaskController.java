package com.SmartTaskAndProjectManagementSystem.Controllers;

import com.SmartTaskAndProjectManagementSystem.Enums.TaskPrioritys;
import com.SmartTaskAndProjectManagementSystem.Enums.TaskStatus;
import com.SmartTaskAndProjectManagementSystem.Util.*;
import com.SmartTaskAndProjectManagementSystem.dto.TaskRequest;
import com.SmartTaskAndProjectManagementSystem.dto.TaskResponse;
import com.SmartTaskAndProjectManagementSystem.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResonse<TaskResponse>> create(
            @Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResonse.success("Task created", taskService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResonse<TaskResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResonse.success("Task fetched", taskService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResonse<Page<TaskResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Pageable pageable = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return ResponseEntity.ok(ApiResonse.success("Tasks fetched", taskService.getAll(pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResonse<TaskResponse>> update(
            @PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        return ResponseEntity.ok(ApiResonse.success("Task updated", taskService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResonse<Void>> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok(ApiResonse.success("Task deleted", null));
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<ApiResonse<Page<TaskResponse>>> getByProject(
            @PathVariable Long projectId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResonse.success("Tasks fetched",
                taskService.getByProject(projectId, pageable)));
    }

    @GetMapping("/by-member/{memberId}")
    public ResponseEntity<ApiResonse<Page<TaskResponse>>> getByMember(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResonse.success("Tasks fetched",
                taskService.getByMember(memberId, pageable)));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResonse<Page<TaskResponse>>> filter(
            @RequestParam(required = false) Long projectId,
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPrioritys priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResonse.success("Filtered tasks",
                taskService.filter(projectId, memberId, status, priority, pageable)));
    }
}