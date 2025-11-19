package com.avk.employeeprojectmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Project title is required")
    private String projectTitle;

    private List<EmployeeSummaryDTO> employees;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeSummaryDTO {
        private Long id;
        private String fullName;
    }
}
