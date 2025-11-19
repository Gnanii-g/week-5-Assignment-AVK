package com.avk.employeeprojectmanagement.controller;

import com.avk.employeeprojectmanagement.dto.EmployeeDTO;
import com.avk.employeeprojectmanagement.dto.ProjectDTO;
import com.avk.employeeprojectmanagement.response.ApiResponse;
import com.avk.employeeprojectmanagement.service.EmployeeService;
import com.avk.employeeprojectmanagement.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ProjectController(ProjectService projectService,
                             EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    // CREATE PROJECT
    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDTO>> createProject(
            @Valid @RequestBody ProjectDTO dto) {

        ProjectDTO saved = projectService.createProject(dto);

        return ResponseEntity.status(201)
                .body(new ApiResponse<>(201, "Project created", saved));
    }

    // GET ALL PROJECTS
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getAllProjects() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Projects fetched", projectService.getAllProjects()));
    }

    // GET EMPLOYEES OF PROJECT
    @GetMapping("/{projectId}/employees")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesByProject(
            @PathVariable Long projectId) {

        List<EmployeeDTO> list = employeeService.getEmployeesByProject(projectId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employees fetched", list));
    }

    // ASSIGN EMPLOYEE → PROJECT
    @PostMapping("/{projectId}/assign/{employeeId}")
    public ResponseEntity<ApiResponse<String>> assignEmployeeToProject(
            @PathVariable Long projectId,
            @PathVariable Long employeeId) {

        projectService.assignEmployeeToProject(projectId, employeeId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee assigned to project", null));
    }

    // DELETE PROJECT
    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponse<String>> deleteProject(@PathVariable Long projectId) {

        projectService.deleteProject(projectId);

        return ResponseEntity.ok(new ApiResponse<>(200, "Project deleted", null));
    }
}
