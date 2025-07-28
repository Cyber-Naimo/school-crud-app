package com.example.crud_school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Subject name is required")
    @Size(min = 2, max = 100, message = "Subject name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(length = 500)
    private String description;
    
    @Size(max = 10, message = "Subject code cannot exceed 10 characters")
    @Column(length = 10, unique = true)
    private String code;
    
    @NotNull(message = "Credits are required")
    @Column(nullable = false)
    private Integer credits;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();
    
    // Constructors
    public Subject() {}
    
    public Subject(String name, String description, String code, Integer credits) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.credits = credits;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public Integer getCredits() {
        return credits;
    }
    
    public void setCredits(Integer credits) {
        this.credits = credits;
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
    
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
    
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
} 