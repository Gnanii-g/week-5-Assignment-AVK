package com.avk.employeeprojectmanagement.controller;

import com.avk.employeeprojectmanagement.dto.EmployeeDTO;
import com.avk.employeeprojectmanagement.dto.ProjectDTO;
import com.avk.employeeprojectmanagement.entity.Employee;
import com.avk.employeeprojectmanagement.response.ApiResponse;
import com.avk.employeeprojectmanagement.service.EmployeeService;
import com.avk.employeeprojectmanagement.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public EmployeeController(EmployeeService employeeService,
                              ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    // CREATE EMPLOYEE
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(
            @Valid @RequestBody Employee employee) {

        Employee saved = employeeService.createEmployee(employee);
        return ResponseEntity
                .status(201)
                .body(new ApiResponse<>(201, "Employee created",
                        com.avk.employeeprojectmanagement.mapper.DtoMapper.toEmployeeDTO(saved)));
    }

    // GET SINGLE EMPLOYEE
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployee(@PathVariable Long id) {
        EmployeeDTO dto = employeeService.getEmployeeDTO(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Employee fetched", dto));
    }

    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee incoming) {

        Employee updated = employeeService.updateEmployee(id, incoming);
        return ResponseEntity.ok(new ApiResponse<>(200, "Employee updated",
                com.avk.employeeprojectmanagement.mapper.DtoMapper.toEmployeeDTO(updated)));
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Employee deleted", null));
    }

    // GET PAGED EMPLOYEES
    @GetMapping
    public ResponseEntity<ApiResponse<Page<EmployeeDTO>>> getPagedEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {

        Page<EmployeeDTO> dtoPage = employeeService.getPagedEmployees(page, size, sort);
        return ResponseEntity.ok(new ApiResponse<>(200, "Employees fetched", dtoPage));
    }

    // GET ALL EMPLOYEES
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> list = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ApiResponse<>(200, "Employees fetched", list));
    }

    // GET PROJECTS OF EMPLOYEE
    @GetMapping("/{id}/projects")
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getProjectsByEmployee(@PathVariable Long id) {
        List<ProjectDTO> projects = projectService.getProjectsByEmployee(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Projects fetched", projects));
    }

}
