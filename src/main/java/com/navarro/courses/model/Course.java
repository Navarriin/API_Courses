package com.navarro.courses.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data // Equivale aos Getters Setters e Constructors
// @Table(name="cursos") Nome da tabela caso nao for igual ao da classe
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 150)
    @Column(length = 150, nullable = false)
    private String name;

    @NotNull
    @Size(max = 15)
    @Column(length = 15, nullable = false)
    private String category;
}
