package com.avk.employeeprojectmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project title cannot be empty")
    @Column(name = "project_title")
    private String projectTitle;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Employee> employees = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    // ⭐ IMPORTANT — The missing method
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.getProjects().add(this);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.getProjects().remove(this);
    }
}
