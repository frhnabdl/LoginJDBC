package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.start.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
