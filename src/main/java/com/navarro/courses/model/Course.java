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
    @Column(length = 150, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String category;
}
