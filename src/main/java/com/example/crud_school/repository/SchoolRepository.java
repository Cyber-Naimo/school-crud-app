package com.example.crud_school.repository;

import com.example.crud_school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    
    Optional<School> findByName(String name);
    
    List<School> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT s FROM School s WHERE s.address LIKE %:city%")
    List<School> findByCity(@Param("city") String city);
    
    boolean existsByName(String name);
    
    @Query("SELECT COUNT(s) FROM School s")
    long countSchools();
} 