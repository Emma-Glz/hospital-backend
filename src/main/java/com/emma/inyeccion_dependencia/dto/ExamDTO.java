package com.emma.inyeccion_dependencia.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExamDTO {

    private Integer idExam;
    @NotNull//no se manda el atributo
    @NotEmpty//el atributo esta vacio
    private String nameExam;
    @NotNull
    @NotEmpty
    private String descriptionExam;

}
