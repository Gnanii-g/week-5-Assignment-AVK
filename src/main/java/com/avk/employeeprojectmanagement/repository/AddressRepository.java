package com.avk.employeeprojectmanagement.repository;

import com.avk.employeeprojectmanagement.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Address entity.
 * Provides basic CRUD operations for Address table.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
