package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.start.models.Region;

//REGION 3
//JPA Repository -> untuk memudahkan dalam crud string
@Repository
public interface RegionRepository extends JpaRepository<Region, Integer>{
    
}
