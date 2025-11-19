package com.avk.employeeprojectmanagement.controller;

import com.avk.employeeprojectmanagement.dto.CompanyDTO;
import com.avk.employeeprojectmanagement.entity.Company;
import com.avk.employeeprojectmanagement.response.ApiResponse;
import com.avk.employeeprojectmanagement.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // CREATE COMPANY
    @PostMapping
    public ResponseEntity<ApiResponse<CompanyDTO>> createCompany(
            @Valid @RequestBody Company company) {

        Company saved = companyService.createCompany(company);
        return ResponseEntity.status(201)
                .body(new ApiResponse<>(201, "Company created",
                        com.avk.employeeprojectmanagement.mapper.DtoMapper.toCompanyDTO(saved)));
    }

    // GET COMPANY + PROJECTS + EMPLOYEES
    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse<CompanyDTO>> getCompany(@PathVariable String name) {
        CompanyDTO dto = companyService.getCompanyDetailsByName(name);
        return ResponseEntity.ok(new ApiResponse<>(200, "Company fetched", dto));
    }

    // DELETE COMPANY
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompanyAndRelatedData(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Company deleted", null));
    }
}
