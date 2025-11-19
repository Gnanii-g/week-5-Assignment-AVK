package com.avk.employeeprojectmanagement.spec;

import com.avk.employeeprojectmanagement.entity.Company;
import com.avk.employeeprojectmanagement.entity.Employee;
import com.avk.employeeprojectmanagement.entity.Project;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class EntitySpecifications {

    // PROJECTS BY EMPLOYEE
    public static Specification<Project> projectsByEmployeeId(Long employeeId) {
        return (root, query, cb) -> {
            Join<Object, Object> join = root.join("employees");
            return cb.equal(join.get("id"), employeeId);
        };
    }

    // PROJECTS BY TITLE
    public static Specification<Project> projectsByTitle(String title) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("projectTitle")), "%" + title.toLowerCase() + "%");
    }

    // EMPLOYEES BY PROJECT
    public static Specification<Employee> employeesByProjectId(Long projectId) {
        return (root, query, cb) ->
                cb.equal(root.join("projects").get("id"), projectId);
    }

    // COMPANY WITH PROJECTS + EMPLOYEES
    public static Specification<Company> companyByName(String name) {
        return (root, query, cb) -> {

            // fetch projects
            var proj = root.fetch("projects", jakarta.persistence.criteria.JoinType.LEFT);

            // fetch employees inside projects
            proj.fetch("employees", jakarta.persistence.criteria.JoinType.LEFT);

            query.distinct(true); // prevent duplicates

            return cb.equal(cb.lower(root.get("companyName")), name.toLowerCase());
        };
    }
}
