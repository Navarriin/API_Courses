package com.navarro.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.navarro.courses.enums.Category;
import com.navarro.courses.enums.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotNull @NotBlank @Size(max = 150) String name,
        @NotNull @Size(max = 15) @ValueOfEnum(enumClass = Category.class) String category,
        @NotNull @Valid List<LessonDTO> lesson
) {
}
