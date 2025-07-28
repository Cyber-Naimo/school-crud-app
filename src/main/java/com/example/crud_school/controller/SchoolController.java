package com.example.crud_school.controller;

import com.example.crud_school.dto.SchoolDto;
import com.example.crud_school.entity.School;
import com.example.crud_school.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/schools")
@Tag(name = "School Management", description = "APIs for managing schools")
public class SchoolController {
    
    private final SchoolService schoolService;
    
    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
    
    @GetMapping
    @Operation(summary = "Get all schools", description = "Retrieve a list of all schools")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved schools",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = SchoolDto.class)))
    })
    public ResponseEntity<List<EntityModel<SchoolDto>>> getAllSchools() {
        List<SchoolDto> schools = schoolService.getAllSchools();
        List<EntityModel<SchoolDto>> schoolModels = schools.stream()
                .map(school -> EntityModel.of(school,
                        linkTo(methodOn(SchoolController.class).getSchoolById(school.getId())).withSelfRel(),
                        linkTo(methodOn(SchoolController.class).getAllSchools()).withRel("schools")))
                .toList();
        
        return ResponseEntity.ok(schoolModels);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get school by ID", description = "Retrieve a school by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved school",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = SchoolDto.class))),
        @ApiResponse(responseCode = "404", description = "School not found")
    })
    public ResponseEntity<EntityModel<SchoolDto>> getSchoolById(
            @Parameter(description = "School ID", required = true) @PathVariable Long id) {
        Optional<SchoolDto> school = schoolService.getSchoolById(id);
        
        return school.map(schoolDto -> {
            EntityModel<SchoolDto> model = EntityModel.of(schoolDto);
            model.add(linkTo(methodOn(SchoolController.class).getSchoolById(id)).withSelfRel());
            model.add(linkTo(methodOn(SchoolController.class).getAllSchools()).withRel("schools"));
            return ResponseEntity.ok(model);
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search schools by name", description = "Search schools by name containing the given string")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved schools",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = SchoolDto.class)))
    })
    public ResponseEntity<List<EntityModel<SchoolDto>>> searchSchoolsByName(
            @Parameter(description = "School name to search for", required = true) 
            @RequestParam String name) {
        List<SchoolDto> schools = schoolService.searchSchoolsByName(name);
        List<EntityModel<SchoolDto>> schoolModels = schools.stream()
                .map(school -> EntityModel.of(school,
                        linkTo(methodOn(SchoolController.class).getSchoolById(school.getId())).withSelfRel(),
                        linkTo(methodOn(SchoolController.class).getAllSchools()).withRel("schools")))
                .toList();
        
        return ResponseEntity.ok(schoolModels);
    }
    
    @GetMapping("/city/{city}")
    @Operation(summary = "Get schools by city", description = "Retrieve schools in a specific city")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved schools",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = SchoolDto.class)))
    })
    public ResponseEntity<List<EntityModel<SchoolDto>>> getSchoolsByCity(
            @Parameter(description = "City name", required = true) @PathVariable String city) {
        List<SchoolDto> schools = schoolService.getSchoolsByCity(city);
        List<EntityModel<SchoolDto>> schoolModels = schools.stream()
                .map(school -> EntityModel.of(school,
                        linkTo(methodOn(SchoolController.class).getSchoolById(school.getId())).withSelfRel(),
                        linkTo(methodOn(SchoolController.class).getAllSchools()).withRel("schools")))
                .toList();
        
        return ResponseEntity.ok(schoolModels);
    }
    
    @PostMapping
    @Operation(summary = "Create a new school", description = "Create a new school with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "School created successfully",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = SchoolDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "School with this name already exists")
    })
    public ResponseEntity<EntityModel<SchoolDto>> createSchool(
            @Parameter(description = "School details", required = true) 
            @Valid @RequestBody School school) {
        try {
            SchoolDto createdSchool = schoolService.createSchool(school);
            EntityModel<SchoolDto> model = EntityModel.of(createdSchool);
            model.add(linkTo(methodOn(SchoolController.class).getSchoolById(createdSchool.getId())).withSelfRel());
            model.add(linkTo(methodOn(SchoolController.class).getAllSchools()).withRel("schools"));
            
            return ResponseEntity.status(HttpStatus.CREATED).body(model);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update a school", description = "Update an existing school with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "School updated successfully",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = SchoolDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "School not found"),
        @ApiResponse(responseCode = "409", description = "School with this name already exists")
    })
    public ResponseEntity<EntityModel<SchoolDto>> updateSchool(
            @Parameter(description = "School ID", required = true) @PathVariable Long id,
            @Parameter(description = "Updated school details", required = true) 
            @Valid @RequestBody School schoolDetails) {
        try {
            Optional<SchoolDto> updatedSchool = schoolService.updateSchool(id, schoolDetails);
            
            return updatedSchool.map(schoolDto -> {
                EntityModel<SchoolDto> model = EntityModel.of(schoolDto);
                model.add(linkTo(methodOn(SchoolController.class).getSchoolById(id)).withSelfRel());
                model.add(linkTo(methodOn(SchoolController.class).getAllSchools()).withRel("schools"));
                return ResponseEntity.ok(model);
            }).orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a school", description = "Delete a school by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "School deleted successfully"),
        @ApiResponse(responseCode = "404", description = "School not found")
    })
    public ResponseEntity<Void> deleteSchool(
            @Parameter(description = "School ID", required = true) @PathVariable Long id) {
        boolean deleted = schoolService.deleteSchool(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/count")
    @Operation(summary = "Get total schools count", description = "Get the total number of schools")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved count")
    })
    public ResponseEntity<Long> getTotalSchoolsCount() {
        long count = schoolService.getTotalSchoolsCount();
        return ResponseEntity.ok(count);
    }
} 