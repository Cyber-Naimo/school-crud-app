package com.example.crud_school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String lastName;
    
    @NotNull(message = "Date of birth is required")
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    @Column(length = 20)
    private String phone;
    
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(length = 100)
    private String email;
    
    @Size(max = 200, message = "Address cannot exceed 200 characters")
    @Column(length = 200)
    private String address;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class clazz;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();
    
    // Constructors
    public Student() {}
    
    public Student(String firstName, String lastName, LocalDate dateOfBirth, String phone, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public School getSchool() {
        return school;
    }
    
    public void setSchool(School school) {
        this.school = school;
    }
    
    public Class getClazz() {
        return clazz;
    }
    
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
    
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
    
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    
    // Helper methods
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
} 