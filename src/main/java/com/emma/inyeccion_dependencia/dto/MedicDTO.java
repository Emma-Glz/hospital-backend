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
public class MedicDTO {

    private Integer idMedic;
    @NotNull//no se manda el atributo
    @NotEmpty//el atributo esta vacio
    private String primaryName;
    @NotNull
    @NotEmpty
    private String surName;
    @NotNull
    @NotEmpty
    private String cmp;
    @NotNull
    @NotEmpty
    private String photo;

}
