package com.example.crud_school.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "School Data Transfer Object")
public class SchoolDto {
    
    @Schema(description = "School ID", example = "1")
    private Long id;
    
    @Schema(description = "School name", example = "Springfield High School")
    private String name;
    
    @Schema(description = "School description", example = "A comprehensive high school")
    private String description;
    
    @Schema(description = "School address", example = "123 Main Street, Springfield")
    private String address;
    
    @Schema(description = "School phone number", example = "+1-555-123-4567")
    private String phone;
    
    @Schema(description = "School email", example = "info@springfield.edu")
    private String email;
    
    @Schema(description = "Number of students in the school")
    private Long studentCount;
    
    @Schema(description = "Number of teachers in the school")
    private Long teacherCount;
    
    @Schema(description = "Number of subjects in the school")
    private Long subjectCount;
    
    @Schema(description = "Number of classes in the school")
    private Long classCount;
    
    // Constructors
    public SchoolDto() {}
    
    public SchoolDto(Long id, String name, String description, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.email = email;
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getStudentCount() {
        return studentCount;
    }
    
    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }
    
    public Long getTeacherCount() {
        return teacherCount;
    }
    
    public void setTeacherCount(Long teacherCount) {
        this.teacherCount = teacherCount;
    }
    
    public Long getSubjectCount() {
        return subjectCount;
    }
    
    public void setSubjectCount(Long subjectCount) {
        this.subjectCount = subjectCount;
    }
    
    public Long getClassCount() {
        return classCount;
    }
    
    public void setClassCount(Long classCount) {
        this.classCount = classCount;
    }
} 