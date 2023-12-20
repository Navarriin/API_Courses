package com.navarro.courses.controller;

import com.navarro.courses.CoursesApplication;
import com.navarro.courses.model.Course;
import com.navarro.courses.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(data -> ResponseEntity.ok().body(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course body) {
        return courseRepository.save(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course body){
        return courseRepository.findById(id)
                .map(data -> {
                    data.setName(body.getName());
                    data.setCategory(body.getCategory());
                    Course updated = courseRepository.save(data);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
