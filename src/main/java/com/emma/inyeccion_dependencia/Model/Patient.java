package com.emma.inyeccion_dependencia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity//(name = "tbl.patient") // from tbl_patient t where t.algo = hace referencia name sombre el query

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include//compara los objetos con su valor y no con su referenica.
    private Integer idPatient;

    @Column(length = 150, nullable = false)
    private String firstName;

    @Column(length = 70, nullable = false)
    private String lastName;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(length = 150)
    private String address;

    @Column(length = 12, nullable = false)
    private String phone;

    @Column(length = 20, nullable = false)
    private String email;

}
