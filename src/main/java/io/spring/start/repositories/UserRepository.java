package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.start.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
