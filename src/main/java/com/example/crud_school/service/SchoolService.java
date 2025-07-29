package com.example.crud_school.service;

import com.example.crud_school.dto.SchoolDto;
import com.example.crud_school.entity.School;
import com.example.crud_school.repository.SchoolRepository;
import com.example.crud_school.repository.StudentRepository;
import com.example.crud_school.repository.TeacherRepository;
import com.example.crud_school.repository.SubjectRepository;
import com.example.crud_school.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SchoolService {
    
    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    
    
    @Autowired
    public SchoolService(SchoolRepository schoolRepository, 
                        StudentRepository studentRepository,
                        TeacherRepository teacherRepository,
                        SubjectRepository subjectRepository,
                        ClassRepository classRepository) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
    }
    
    public List<SchoolDto> getAllSchools() {
        return schoolRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public Optional<SchoolDto> getSchoolById(Long id) {
        return schoolRepository.findById(id)
                .map(this::convertToDto);
    }
    
    public Optional<SchoolDto> getSchoolByName(String name) {
        return schoolRepository.findByName(name)
                .map(this::convertToDto);
    }
    
    public List<SchoolDto> searchSchoolsByName(String name) {
        return schoolRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public List<SchoolDto> getSchoolsByCity(String city) {
        return schoolRepository.findByCity(city).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public SchoolDto createSchool(School school) {
        if (schoolRepository.existsByName(school.getName())) {
            throw new RuntimeException("School with name '" + school.getName() + "' already exists");
        }
        School savedSchool = schoolRepository.save(school);
        return convertToDto(savedSchool);
    }
    
    public Optional<SchoolDto> updateSchool(Long id, School schoolDetails) {
        return schoolRepository.findById(id)
                .map(existingSchool -> {
                    // Check if the new name conflicts with another school
                    if (!existingSchool.getName().equals(schoolDetails.getName()) &&
                        schoolRepository.existsByName(schoolDetails.getName())) {
                        throw new RuntimeException("School with name '" + schoolDetails.getName() + "' already exists");
                    }
                    
                    existingSchool.setName(schoolDetails.getName());
                    existingSchool.setDescription(schoolDetails.getDescription());
                    existingSchool.setAddress(schoolDetails.getAddress());
                    existingSchool.setPhone(schoolDetails.getPhone());
                    existingSchool.setEmail(schoolDetails.getEmail());
                    
                    School updatedSchool = schoolRepository.save(existingSchool);
                    return convertToDto(updatedSchool);
                });
    }
    
    public boolean deleteSchool(Long id) {
        if (schoolRepository.existsById(id)) {
            schoolRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public long getTotalSchoolsCount() {
        return schoolRepository.countSchools();
    }
    
    private SchoolDto convertToDto(School school) {
        SchoolDto dto = new SchoolDto(
                school.getId(),
                school.getName(),
                school.getDescription(),
                school.getAddress(),
                school.getPhone(),
                school.getEmail()
        );
        
        // Add counts
        dto.setStudentCount(studentRepository.countBySchoolId(school.getId()));
        dto.setTeacherCount(teacherRepository.countBySchoolId(school.getId()));
        dto.setSubjectCount(subjectRepository.countBySchoolId(school.getId()));
        dto.setClassCount(classRepository.countBySchoolId(school.getId()));
        
        return dto;
    }
} 