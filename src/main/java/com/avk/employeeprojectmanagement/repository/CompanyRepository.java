package com.avk.employeeprojectmanagement.repository;

import com.avk.employeeprojectmanagement.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CompanyRepository
        extends JpaRepository<Company, Long>,
        JpaSpecificationExecutor<Company> {

    Optional<Company> findByCompanyName(String companyName);
}
