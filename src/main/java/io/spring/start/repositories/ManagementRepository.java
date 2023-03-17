package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.spring.start.models.Employee;
import io.spring.start.models.User;

@Repository
public interface ManagementRepository extends JpaRepository<Employee, Integer>{
    // login -> mencoba query nya dulu
    // @Query("""
    //         SELECT 
    //             new io.spring.start.dto.Login() User u
    //                 JOIN u.Employee e ON u.id == e.id
    //                 JOIN u.Role r ON u.Role.id == r.id
    //                 WHERE e.email = :email
    //         """) 
    
    //     SELECT 
    // 	e.email, u.password, r.name
    // FROM 
    // 	tb_m_user u 
    // 		JOIN tb_m_employee e ON u.id = e.id	
    //         JOIN tb_m_role r ON e.id = r.id
    // WHERE 
    // 	e.email = "farhan@gmail.com";   
    //@Query(value = "SELECT new io.spring.start.dto.Login(e.email, u.password, r.name) FROM User u JOIN Employee e ON u.id = e.id JOIN Role r ON e.id = r.id WHERE e.email = :email")    
    
    // login
    //asli
    // @Query(value = "SELECT e.fullname, e.email, r.name, u.password FROM tb_m_user u JOIN tb_m_role r ON u.role_id == r.id JOIN tb_m_employee e ON u.id == e.id WHERE e.email == :email", nativeQuery = true)
    //coba
    @Query(value = "SELECT u, r FROM User u JOIN u.employee e JOIN u.role r WHERE e.email = ?1")
    public User Login(String email);
    
    // register
    
    // forgot password
    // change password
}
