package com.avk.employeeprojectmanagement.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompanyDTO {

    private Long id;
    private String companyName;
    private List<ProjectDTO> projects;
}
