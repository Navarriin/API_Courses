package com.navarro.courses.controller;

import com.navarro.courses.dto.CourseDTO;
import com.navarro.courses.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
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
    public List<CourseDTO> list(){
        return courseService.list();
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable @NotNull @Positive Long id) {
        return courseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid CourseDTO body) {
        return courseService.create(body);
    }

    @PutMapping("/{id}")
    public CourseDTO update(
            @PathVariable @NotNull @Positive Long id, @RequestBody @Valid CourseDTO body
    ) {
        return courseService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
            courseService.delete(id);
    }
}
