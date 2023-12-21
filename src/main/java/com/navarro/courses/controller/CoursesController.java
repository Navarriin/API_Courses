package com.navarro.courses.controller;

import com.navarro.courses.model.Course;
import com.navarro.courses.service.CourseService;
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

    private final CourseService courseService;
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<Course> list(){
        return courseService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable @NotNull @Positive Long id) {
        return courseService.getById(id)
                .map(data -> ResponseEntity.ok().body(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course body) {
        return courseService.create(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(
            @PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid Course body
    ) {
        return courseService.update(id, body)
                .map(data -> ResponseEntity.ok().body(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
            if(courseService.delete(id)) {
                return ResponseEntity.noContent().<Void>build();
            }
            return ResponseEntity.notFound().build();
    }
}
