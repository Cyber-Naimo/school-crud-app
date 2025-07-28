package com.example.crud_school.repository;

import com.example.crud_school.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    
    List<Enrollment> findByStudentId(Long studentId);
    
    List<Enrollment> findBySubjectId(Long subjectId);
    
    List<Enrollment> findByClazzId(Long classId);
    
    List<Enrollment> findByStatus(String status);
    
    List<Enrollment> findByEnrollmentDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.subject.id = :subjectId")
    List<Enrollment> findByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
    
    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.status = :status")
    List<Enrollment> findByStudentIdAndStatus(@Param("studentId") Long studentId, @Param("status") String status);
    
    @Query("SELECT e FROM Enrollment e WHERE e.subject.id = :subjectId AND e.status = :status")
    List<Enrollment> findBySubjectIdAndStatus(@Param("subjectId") Long subjectId, @Param("status") String status);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.student.id = :studentId")
    long countByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.subject.id = :subjectId")
    long countBySubjectId(@Param("subjectId") Long subjectId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.status = :status")
    long countByStatus(@Param("status") String status);
} 