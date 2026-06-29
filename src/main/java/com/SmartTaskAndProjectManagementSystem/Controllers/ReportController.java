package com.SmartTaskAndProjectManagementSystem.Controllers;


import com.SmartTaskAndProjectManagementSystem.Util.ApiResonse;
import com.SmartTaskAndProjectManagementSystem.dto.TaskResponse;
import com.SmartTaskAndProjectManagementSystem.service.WorkloadResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final com.SmartTaskAndProjectManagementSystem.service.ReportService reportService;

    @GetMapping("/completed-tasks")
    public ResponseEntity<ApiResonse<List<TaskResponse>>> completedTasksBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(ApiResonse.success("Completed tasks report",
                reportService.completedTasksBetween(start, end)));
    }

    @GetMapping("/pending-tasks/{projectId}")
    public ResponseEntity<ApiResonse<List<TaskResponse>>> pendingTasksByProject(
            @PathVariable Long projectId) {
        return ResponseEntity.ok(ApiResonse.success("Pending tasks report",
                reportService.pendingTasksByProject(projectId)));
    }

    @GetMapping("/member-workload")
    public ResponseEntity<ApiResonse<List<WorkloadResponse>>> memberWorkload() {
        return ResponseEntity.ok(ApiResonse.success("Member workload report",
                reportService.memberWorkloadReport()));
    }
}