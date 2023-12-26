package com.navarro.courses.dto.mapper;

import com.navarro.courses.dto.CourseDTO;
import com.navarro.courses.dto.LessonDTO;
import com.navarro.courses.enums.Category;
import com.navarro.courses.model.Course;
import com.navarro.courses.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course) {
        if(course == null){
            return null;
        }
        List<LessonDTO> lessonDTOS = course.getLessons()
                .stream()
                .map(lesson ->
                    new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl())
                )
                .collect(Collectors.toList());

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessonDTOS);
    }

    public Course toEntity(CourseDTO courseDTO) {
        if(courseDTO == null){
            return null;
        }
        Course course = new Course();
        if(courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));

        List<Lesson> lessons = courseDTO.lesson()
                .stream().map(lessonDTO -> {
                    var lesson = new Lesson();
                    lesson.setId(lessonDTO.id());
                    lesson.setName(lessonDTO.name());
                    lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
                    lesson.setCourse(course);
                    return lesson;
                }).collect(Collectors.toList());
        course.setLessons(lessons);
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "front-end" -> Category.FRONT_END;
            case "back-end" -> Category.BACK_END;
            case "full-stack" -> Category.FULL_STACK;
            default -> throw new IllegalArgumentException("Categoria inválida " + value);
        };
    }
}
