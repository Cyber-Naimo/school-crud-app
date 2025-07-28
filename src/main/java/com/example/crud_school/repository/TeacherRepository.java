package com.example.crud_school.repository;

import com.example.crud_school.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
    List<Teacher> findBySchoolId(Long schoolId);
    
    Optional<Teacher> findByEmail(String email);
    
    Optional<Teacher> findByEmployeeId(String employeeId);
    
    List<Teacher> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    
    List<Teacher> findByQualificationContainingIgnoreCase(String qualification);
    
    @Query("SELECT t FROM Teacher t WHERE t.hireDate BETWEEN :startDate AND :endDate")
    List<Teacher> findByHireDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT COUNT(t) FROM Teacher t WHERE t.school.id = :schoolId")
    long countBySchoolId(@Param("schoolId") Long schoolId);
    
    @Query("SELECT t FROM Teacher t WHERE t.school.id = :schoolId AND t.qualification = :qualification")
    List<Teacher> findBySchoolIdAndQualification(@Param("schoolId") Long schoolId, @Param("qualification") String qualification);
} 