package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.start.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
