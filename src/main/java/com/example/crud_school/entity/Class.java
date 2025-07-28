package com.example.crud_school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class Class {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Class name is required")
    @Size(min = 2, max = 50, message = "Class name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String name;
    
    @Size(max = 10, message = "Class code cannot exceed 10 characters")
    @Column(length = 10, unique = true)
    private String code;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(length = 500)
    private String description;
    
    @NotNull(message = "Grade level is required")
    @Column(nullable = false)
    private Integer gradeLevel;
    
    @NotNull(message = "Academic year is required")
    @Column(nullable = false)
    private Integer academicYear;
    
    @Size(max = 20, message = "Room number cannot exceed 20 characters")
    @Column(length = 20)
    private String roomNumber;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    
    @OneToMany(mappedBy = "clazz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
    
    @OneToMany(mappedBy = "clazz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();
    
    // Constructors
    public Class() {}
    
    public Class(String name, String code, String description, Integer gradeLevel, Integer academicYear, String roomNumber) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.gradeLevel = gradeLevel;
        this.academicYear = academicYear;
        this.roomNumber = roomNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getGradeLevel() {
        return gradeLevel;
    }
    
    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    
    public Integer getAcademicYear() {
        return academicYear;
    }
    
    public void setAcademicYear(Integer academicYear) {
        this.academicYear = academicYear;
    }
    
    public String getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public School getSchool() {
        return school;
    }
    
    public void setSchool(School school) {
        this.school = school;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
    
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    
    // Helper methods
    public String getFullClassName() {
        return name + " (Grade " + gradeLevel + ", Year " + academicYear + ")";
    }
} 