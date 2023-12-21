package com.navarro.courses.service;

import com.navarro.courses.exceptions.RecordNotFoundException;
import com.navarro.courses.model.Course;
import com.navarro.courses.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list(){
        return courseRepository.findAll();
    }

    public Course getById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(@Valid Course body) {
        return courseRepository.save(body);
    }

    public Course update( @NotNull @Positive Long id, @Valid Course body) {
        return courseRepository.findById(id)
                .map(data -> {
                    data.setName(body.getName());
                    data.setCategory(body.getCategory());
                    return courseRepository.save(data);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    public void delete(@NotNull @Positive Long id){
        courseRepository.delete(courseRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
