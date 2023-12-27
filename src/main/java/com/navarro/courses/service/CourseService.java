package com.navarro.courses.service;

import com.navarro.courses.dto.CourseDTO;
import com.navarro.courses.dto.CoursePageDTO;
import com.navarro.courses.dto.mapper.CourseMapper;
import com.navarro.courses.exceptions.RecordNotFoundException;
import com.navarro.courses.model.Course;
import com.navarro.courses.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDTO list(
            @RequestParam(defaultValue = "0") @PositiveOrZero int pageNumber,
            @RequestParam(defaultValue = "10") @Positive @Max(15) int pageSize) {
        Page<Course> page = courseRepository.findAll(PageRequest.of(pageNumber,pageSize));
        List<CourseDTO> courses = page.get().map(courseMapper::toDTO)
                .collect(Collectors.toList());
        return new CoursePageDTO(courses, page.getTotalElements(), page.getTotalPages());
    }

    public CourseDTO getById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO body) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(body)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid CourseDTO body) {
        return courseRepository.findById(id)
                .map(data -> {
                    Course course = courseMapper.toEntity(body);
                    data.setName(body.name());
                    data.setCategory(courseMapper.convertCategoryValue(body.category()));
                    data.getLessons().clear();
                    course.getLessons().forEach(lesson ->
                            data.getLessons().add(lesson));
                    return courseMapper.toDTO(courseRepository.save(data));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    public void delete(@NotNull @Positive Long id){
        courseRepository.delete(courseRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
