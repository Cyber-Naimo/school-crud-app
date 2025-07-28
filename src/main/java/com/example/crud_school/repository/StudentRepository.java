package com.example.crud_school.repository;

import com.example.crud_school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    List<Student> findBySchoolId(Long schoolId);
    
    List<Student> findByClazzId(Long classId);
    
    Optional<Student> findByEmail(String email);
    
    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    
    @Query("SELECT s FROM Student s WHERE s.dateOfBirth BETWEEN :startDate AND :endDate")
    List<Student> findByDateOfBirthBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT s FROM Student s WHERE s.school.id = :schoolId AND s.clazz.id = :classId")
    List<Student> findBySchoolIdAndClassId(@Param("schoolId") Long schoolId, @Param("classId") Long classId);
    
    @Query("SELECT COUNT(s) FROM Student s WHERE s.school.id = :schoolId")
    long countBySchoolId(@Param("schoolId") Long schoolId);
    
    @Query("SELECT COUNT(s) FROM Student s WHERE s.clazz.id = :classId")
    long countByClassId(@Param("classId") Long classId);
} 