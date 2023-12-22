package com.navarro.courses.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.navarro.courses.enums.Category;
import com.navarro.courses.enums.Status;
import com.navarro.courses.enums.converters.CategoryConverter;
import com.navarro.courses.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
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
    @Column(length = 15, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;
}
