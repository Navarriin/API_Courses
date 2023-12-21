package com.navarro.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotNull @NotBlank @Size(max = 150) String name,
        @NotNull @Size(max = 15) String category
) {
}
