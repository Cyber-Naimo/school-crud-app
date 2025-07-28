package com.example.crud_school.repository;

import com.example.crud_school.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
    List<Subject> findBySchoolId(Long schoolId);
    
    List<Subject> findByTeacherId(Long teacherId);
    
    Optional<Subject> findByCode(String code);
    
    List<Subject> findByNameContainingIgnoreCase(String name);
    
    List<Subject> findByCredits(Integer credits);
    
    @Query("SELECT s FROM Subject s WHERE s.school.id = :schoolId AND s.teacher.id = :teacherId")
    List<Subject> findBySchoolIdAndTeacherId(@Param("schoolId") Long schoolId, @Param("teacherId") Long teacherId);
    
    @Query("SELECT COUNT(s) FROM Subject s WHERE s.school.id = :schoolId")
    long countBySchoolId(@Param("schoolId") Long schoolId);
    
    @Query("SELECT COUNT(s) FROM Subject s WHERE s.teacher.id = :teacherId")
    long countByTeacherId(@Param("teacherId") Long teacherId);
} 