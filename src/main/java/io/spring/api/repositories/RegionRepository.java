package io.spring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.spring.api.models.Region;

public interface RegionRepository extends JpaRepository<Region,Integer>{
    
}
