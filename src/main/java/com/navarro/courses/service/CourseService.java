package com.navarro.courses.service;

import com.navarro.courses.dto.CourseDTO;
import com.navarro.courses.dto.mapper.CourseMapper;
import com.navarro.courses.exceptions.RecordNotFoundException;
import com.navarro.courses.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    public List<CourseDTO> list(){
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO body) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(body)));
    }

    public CourseDTO update( @NotNull @Positive Long id, @Valid CourseDTO body) {
        return courseRepository.findById(id)
                .map(data -> {
                    data.setName(body.name());
                    data.setCategory(body.category());
                    return courseMapper.toDTO(courseRepository.save(data));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    public void delete(@NotNull @Positive Long id){
        courseRepository.delete(courseRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
