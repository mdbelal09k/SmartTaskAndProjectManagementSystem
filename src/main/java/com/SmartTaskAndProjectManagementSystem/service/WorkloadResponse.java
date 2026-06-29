package com.SmartTaskAndProjectManagementSystem.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkloadResponse {

    private Long memberId;
    private String memberName;
    private Long taskCount;
}
