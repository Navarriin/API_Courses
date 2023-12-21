package com.navarro.courses.service;

import com.navarro.courses.model.Course;
import com.navarro.courses.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

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

    public Optional<Course> getById(@NotNull @Positive Long id) {
        return courseRepository.findById(id);
    }

    public Course create(@Valid Course body) {
        return courseRepository.save(body);
    }

    public Optional<Course> update( @NotNull @Positive Long id, @Valid Course body) {
        return courseRepository.findById(id)
                .map(data -> {
                    data.setName(body.getName());
                    data.setCategory(body.getCategory());
                    return courseRepository.save(data);
        });
    }
    public boolean delete(@NotNull @Positive Long id){
        return courseRepository.findById(id)
                .map(data -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
