package com.SmartTaskAndProjectManagementSystem.Controllers;


import com.SmartTaskAndProjectManagementSystem.Util.ApiResonse;
import com.SmartTaskAndProjectManagementSystem.dto.DashboardResponse;
import com.SmartTaskAndProjectManagementSystem.service.DashboardService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResonse<DashboardResponse>> getDashboard() {
        return ResponseEntity.ok(ApiResonse.success("Dashboard fetched",
                dashboardService.getDashboard()));
    }
}
