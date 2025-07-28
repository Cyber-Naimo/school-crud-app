package com.example.crud_school.config;

import com.example.crud_school.entity.*;
import com.example.crud_school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private SchoolRepository schoolRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private ClassRepository classRepository;
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Create sample schools
        School school1 = new School("Springfield High School", "A comprehensive high school", 
                                   "123 Main Street, Springfield", "+1-555-123-4567", "info@springfield.edu");
        School school2 = new School("Lincoln Academy", "An academic excellence institution", 
                                   "456 Oak Avenue, Lincoln", "+1-555-987-6543", "info@lincoln.edu");
        
        schoolRepository.save(school1);
        schoolRepository.save(school2);
        
        // Create sample teachers
        Teacher teacher1 = new Teacher("John", "Smith", LocalDate.of(1980, 5, 15), 
                                     "+1-555-111-1111", "john.smith@springfield.edu", 
                                     "123 Teacher St, Springfield", "Master's in Mathematics", "T001", 
                                     LocalDate.of(2015, 8, 1));
        Teacher teacher2 = new Teacher("Sarah", "Johnson", LocalDate.of(1985, 3, 20), 
                                     "+1-555-222-2222", "sarah.johnson@springfield.edu", 
                                     "456 Teacher Ave, Springfield", "PhD in Physics", "T002", 
                                     LocalDate.of(2018, 9, 1));
        Teacher teacher3 = new Teacher("Michael", "Brown", LocalDate.of(1975, 7, 10), 
                                     "+1-555-333-3333", "michael.brown@lincoln.edu", 
                                     "789 Teacher Blvd, Lincoln", "Master's in English", "T003", 
                                     LocalDate.of(2010, 8, 15));
        
        teacher1.setSchool(school1);
        teacher2.setSchool(school1);
        teacher3.setSchool(school2);
        
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);
        teacherRepository.save(teacher3);
        
        // Create sample subjects
        Subject subject1 = new Subject("Mathematics", "Advanced mathematics including calculus", "MATH101", 4);
        Subject subject2 = new Subject("Physics", "Introduction to physics concepts", "PHYS101", 3);
        Subject subject3 = new Subject("English Literature", "Study of classic literature", "ENG101", 3);
        Subject subject4 = new Subject("Chemistry", "Basic chemistry principles", "CHEM101", 4);
        
        subject1.setSchool(school1);
        subject1.setTeacher(teacher1);
        subject2.setSchool(school1);
        subject2.setTeacher(teacher2);
        subject3.setSchool(school2);
        subject3.setTeacher(teacher3);
        subject4.setSchool(school1);
        
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        subjectRepository.save(subject3);
        subjectRepository.save(subject4);
        
        // Create sample classes
        com.example.crud_school.entity.Class class1 = new com.example.crud_school.entity.Class("Grade 10A", "G10A", "Grade 10 Section A", 10, 2024, "Room 101");
        com.example.crud_school.entity.Class class2 = new com.example.crud_school.entity.Class("Grade 11B", "G11B", "Grade 11 Section B", 11, 2024, "Room 102");
        com.example.crud_school.entity.Class class3 = new com.example.crud_school.entity.Class("Grade 12A", "G12A", "Grade 12 Section A", 12, 2024, "Room 103");
        
        class1.setSchool(school1);
        class1.setTeacher(teacher1);
        class2.setSchool(school1);
        class2.setTeacher(teacher2);
        class3.setSchool(school2);
        class3.setTeacher(teacher3);
        
        classRepository.save(class1);
        classRepository.save(class2);
        classRepository.save(class3);
        
        // Create sample students
        Student student1 = new Student("Alice", "Johnson", LocalDate.of(2008, 6, 15), 
                                     "+1-555-444-4444", "alice.johnson@student.edu", "123 Student St");
        Student student2 = new Student("Bob", "Williams", LocalDate.of(2007, 9, 22), 
                                     "+1-555-555-5555", "bob.williams@student.edu", "456 Student Ave");
        Student student3 = new Student("Charlie", "Davis", LocalDate.of(2006, 4, 8), 
                                     "+1-555-666-6666", "charlie.davis@student.edu", "789 Student Blvd");
        Student student4 = new Student("Diana", "Miller", LocalDate.of(2008, 12, 3), 
                                     "+1-555-777-7777", "diana.miller@student.edu", "321 Student Rd");
        
        student1.setSchool(school1);
        student1.setClazz(class1);
        student2.setSchool(school1);
        student2.setClazz(class1);
        student3.setSchool(school1);
        student3.setClazz(class2);
        student4.setSchool(school2);
        student4.setClazz(class3);
        
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        
        // Create sample enrollments
        Enrollment enrollment1 = new Enrollment(student1, subject1, class1);
        Enrollment enrollment2 = new Enrollment(student1, subject2, class1);
        Enrollment enrollment3 = new Enrollment(student2, subject1, class1);
        Enrollment enrollment4 = new Enrollment(student3, subject2, class2);
        Enrollment enrollment5 = new Enrollment(student4, subject3, class3);
        
        enrollmentRepository.save(enrollment1);
        enrollmentRepository.save(enrollment2);
        enrollmentRepository.save(enrollment3);
        enrollmentRepository.save(enrollment4);
        enrollmentRepository.save(enrollment5);
        
        System.out.println("Sample data initialized successfully!");
    }
} 