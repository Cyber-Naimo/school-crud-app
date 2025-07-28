package com.example.crud_school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schools")
public class School {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "School name is required")
    @Size(min = 2, max = 100, message = "School name must be between 2 and 100 characters")
    @Column(nullable = false, unique = true)
    private String name;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(length = 500)
    private String description;
    
    @Size(max = 200, message = "Address cannot exceed 200 characters")
    @Column(length = 200)
    private String address;
    
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    @Column(length = 20)
    private String phone;
    
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(length = 100)
    private String email;
    
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
    
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Teacher> teachers = new ArrayList<>();
    
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects = new ArrayList<>();
    
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Class> classes = new ArrayList<>();
    
    // Constructors
    public School() {}
    
    public School(String name, String description, String address, String phone, String email) {
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
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    public List<Teacher> getTeachers() {
        return teachers;
    }
    
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }
    
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    public List<Class> getClasses() {
        return classes;
    }
    
    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
    
    // Helper methods
    public void addStudent(Student student) {
        students.add(student);
        student.setSchool(this);
    }
    
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.setSchool(this);
    }
    
    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.setSchool(this);
    }
    
    public void addClass(Class clazz) {
        classes.add(clazz);
        clazz.setSchool(this);
    }
} 