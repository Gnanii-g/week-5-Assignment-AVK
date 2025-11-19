package com.avk.employeeprojectmanagement.service;

import com.avk.employeeprojectmanagement.dto.ProjectDTO;
import com.avk.employeeprojectmanagement.entity.Employee;
import com.avk.employeeprojectmanagement.entity.Project;
import com.avk.employeeprojectmanagement.exception.ResourceNotFoundException;
import com.avk.employeeprojectmanagement.mapper.DtoMapper;
import com.avk.employeeprojectmanagement.repository.EmployeeRepository;
import com.avk.employeeprojectmanagement.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository,
                          EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    // CREATE PROJECT
    @Transactional
    public ProjectDTO createProject(ProjectDTO dto) {

        Project project = new Project();
        project.setProjectTitle(dto.getProjectTitle());

        Project saved = projectRepository.save(project);

        return DtoMapper.toProjectDTO(saved);
    }

    // GET ALL PROJECTS
    @Transactional
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(DtoMapper::toProjectDTO)
                .collect(Collectors.toList());
    }

    // GET PROJECTS BY EMPLOYEE ID
    @Transactional
    public List<ProjectDTO> getProjectsByEmployee(Long employeeId) {
        return projectRepository.findAll()
                .stream()
                .filter(p -> p.getEmployees()
                        .stream()
                        .anyMatch(e -> e.getId().equals(employeeId)))
                .map(DtoMapper::toProjectDTO)
                .collect(Collectors.toList());
    }

    // ASSIGN EMPLOYEE TO PROJECT
    @Transactional
    public void assignEmployeeToProject(Long projectId, Long employeeId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        project.getEmployees().add(employee);
        employee.getProjects().add(project);

        projectRepository.save(project);
    }

    // DELETE PROJECT
    @Transactional
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
