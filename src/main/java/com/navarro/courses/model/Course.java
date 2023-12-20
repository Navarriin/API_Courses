package com.navarro.courses.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Equivale aos Getters Setters e Constructors
// @Table(name="cursos") Nome da tabela caso nao for igual ao da classe
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    private String name;

    private String category;
}
