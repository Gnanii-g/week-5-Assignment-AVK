package com.avk.employeeprojectmanagement.mapper;

import com.avk.employeeprojectmanagement.dto.CompanyDTO;
import com.avk.employeeprojectmanagement.dto.EmployeeDTO;
import com.avk.employeeprojectmanagement.dto.ProjectDTO;
import com.avk.employeeprojectmanagement.entity.Company;
import com.avk.employeeprojectmanagement.entity.Employee;
import com.avk.employeeprojectmanagement.entity.Project;

import java.util.Collections;
import java.util.stream.Collectors;

public class DtoMapper {


    // EMPLOYEE → EmployeeDTO

    public static EmployeeDTO toEmployeeDTO(Employee e) {
        if (e == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(e.getId());
        dto.setFullName(e.getFullName());

        if (e.getAddress() != null) {
            dto.setStreet(e.getAddress().getStreet());
            dto.setCity(e.getAddress().getCity());
            dto.setState(e.getAddress().getState());
            dto.setPostalCode(e.getAddress().getPostalCode());
        }

        dto.setProjects(
                e.getProjects() == null ? Collections.emptyList() :
                        e.getProjects().stream()
                                .map(p -> new EmployeeDTO.ProjectSummaryDTO(
                                        p.getId(),
                                        p.getProjectTitle()
                                ))
                                .collect(Collectors.toList())
        );

        return dto;
    }


    // PROJECT → ProjectDTO

    public static ProjectDTO toProjectDTO(Project p) {
        if (p == null) return null;

        ProjectDTO dto = new ProjectDTO();
        dto.setId(p.getId());
        dto.setProjectTitle(p.getProjectTitle());   // <-- FIXED ✔

        dto.setEmployees(
                p.getEmployees() == null ? Collections.emptyList() :
                        p.getEmployees().stream()
                                .map(e -> new ProjectDTO.EmployeeSummaryDTO(
                                        e.getId(),
                                        e.getFullName()
                                ))
                                .collect(Collectors.toList())
        );

        return dto;
    }


    // COMPANY → CompanyDTO

    public static CompanyDTO toCompanyDTO(Company c) {
        if (c == null) return null;

        CompanyDTO dto = new CompanyDTO();
        dto.setId(c.getId());
        dto.setCompanyName(c.getCompanyName());

        dto.setProjects(
                c.getProjects() == null ? Collections.emptyList() :
                        c.getProjects().stream()
                                .map(DtoMapper::toProjectDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }
}
