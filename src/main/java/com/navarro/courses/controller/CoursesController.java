package com.navarro.courses.controller;

import com.navarro.courses.CoursesApplication;
import com.navarro.courses.model.Course;
import com.navarro.courses.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    private final CourseRepository courseRepository;
    public CoursesController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> list(){
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(data -> ResponseEntity.ok().body(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course body) {
        return courseRepository.save(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(
            @PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid Course body) {

        return courseRepository.findById(id)
                .map(data -> {
                    data.setName(body.getName());
                    data.setCategory(body.getCategory());
                    Course updated = courseRepository.save(data);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
        return courseRepository.findById(id)
                .map(data -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
