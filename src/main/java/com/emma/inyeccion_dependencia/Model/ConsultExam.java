package com.emma.inyeccion_dependencia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(value = ConsultExamPK.class)
public class ConsultExam {
    @Id
    private Consult consult;

    @Id
    private Exam exam;
}
