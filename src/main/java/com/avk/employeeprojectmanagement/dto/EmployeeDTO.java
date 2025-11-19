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
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    // Flattened address
    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    private List<ProjectSummaryDTO> projects;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectSummaryDTO {
        private Long id;
        private String projectTitle; // renamed for consistency
    }
}
