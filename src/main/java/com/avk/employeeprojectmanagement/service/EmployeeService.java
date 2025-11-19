package com.avk.employeeprojectmanagement.service;

import com.avk.employeeprojectmanagement.dto.EmployeeDTO;
import com.avk.employeeprojectmanagement.entity.Employee;
import com.avk.employeeprojectmanagement.exception.ResourceNotFoundException;
import com.avk.employeeprojectmanagement.mapper.DtoMapper;
import com.avk.employeeprojectmanagement.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE EMPLOYEE
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // GET SINGLE EMPLOYEE DTO
    @Transactional
    public EmployeeDTO getEmployeeDTO(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return DtoMapper.toEmployeeDTO(emp);
    }

    // UPDATE EMPLOYEE (INCLUDING ADDRESS)
    @Transactional
    public Employee updateEmployee(Long id, Employee incoming) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        existing.setFullName(incoming.getFullName());
        existing.setAddress(incoming.getAddress());

        return employeeRepository.save(existing);
    }

    // DELETE EMPLOYEE
    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    // GET PAGED EMPLOYEES
    @Transactional
    public Page<EmployeeDTO> getPagedEmployees(int page, int size, String sort) {

        String[] params = sort.split(",");
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(params[1]), params[0])
        );

        return employeeRepository.findAll(pageable)
                .map(DtoMapper::toEmployeeDTO);
    }

    // GET ALL EMPLOYEES
    @Transactional
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(DtoMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    // REQUIRED BY PROJECT CONTROLLER
    @Transactional
    public List<EmployeeDTO> getEmployeesByProject(Long projectId) {
        return employeeRepository.findAll().stream()
                .filter(e -> e.getProjects().stream()
                        .anyMatch(p -> p.getId().equals(projectId)))
                .map(DtoMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
