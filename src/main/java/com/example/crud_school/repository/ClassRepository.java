package com.example.crud_school.repository;

import com.example.crud_school.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    
    // Basic queries
    List<Class> findBySchoolId(Long schoolId);
    
    List<Class> findByTeacherId(Long teacherId);
    
    Optional<Class> findByCode(String code);
    
    List<Class> findByNameContainingIgnoreCase(String name);
    
    List<Class> findByGradeLevel(Integer gradeLevel);
    
    List<Class> findByAcademicYear(Integer academicYear);
    
    // Custom queries
    @Query("SELECT c FROM Class c WHERE c.school.id = :schoolId AND c.gradeLevel = :gradeLevel")
    List<Class> findBySchoolIdAndGradeLevel(@Param("schoolId") Long schoolId, @Param("gradeLevel") Integer gradeLevel);
    
    @Query("SELECT c FROM Class c WHERE c.school.id = :schoolId AND c.academicYear = :academicYear")
    List<Class> findBySchoolIdAndAcademicYear(@Param("schoolId") Long schoolId, @Param("academicYear") Integer academicYear);
    
    @Query("SELECT COUNT(c) FROM Class c WHERE c.school.id = :schoolId")
    long countBySchoolId(@Param("schoolId") Long schoolId);
    
    @Query("SELECT COUNT(c) FROM Class c WHERE c.teacher.id = :teacherId")
    long countByTeacherId(@Param("teacherId") Long teacherId);
} 