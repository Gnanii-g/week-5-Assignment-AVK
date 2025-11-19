package com.avk.employeeprojectmanagement.service;

import com.avk.employeeprojectmanagement.dto.CompanyDTO;
import com.avk.employeeprojectmanagement.entity.Company;
import com.avk.employeeprojectmanagement.exception.ResourceNotFoundException;
import com.avk.employeeprojectmanagement.mapper.DtoMapper;
import com.avk.employeeprojectmanagement.repository.CompanyRepository;
import com.avk.employeeprojectmanagement.spec.EntitySpecifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // CREATE COMPANY
    @Transactional
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    // GET COMPANY + PROJECTS + EMPLOYEES (returns DTO)
    @Transactional(readOnly = true)
    public CompanyDTO getCompanyDetailsByName(String name) {

        Company company = companyRepository.findOne(
                EntitySpecifications.companyByName(name)
        ).orElseThrow(() -> new ResourceNotFoundException("Company not found: " + name));

        return DtoMapper.toCompanyDTO(company);
    }

    // DELETE COMPANY + PROJECTS + EMPLOYEES + ADDRESSES (Cascade)
    @Transactional
    public void deleteCompanyAndRelatedData(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        companyRepository.delete(company);
    }
}
