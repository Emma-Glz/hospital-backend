package com.emma.inyeccion_dependencia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idConsult;
    @ManyToOne
    @JoinColumn(name = "id_patient",nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_PATIENT"))
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_medic",nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_MEDIC"))
    private Medic medic ;

    @ManyToOne
    @JoinColumn(name = "id_specialty",nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_SPECIALTY"))
    private Specialty specialty;

    @Column(length = 3, nullable = false)
    private String numConsult;

    @Column(nullable = false)
    private LocalDateTime consultDate;

    //MAESTRO DETALLE
    //MAPPEDbYENLACE CON SU CONTRAPARTE   consult hace referencia al campo de consultDetail   orphan me ayuda para poder remover de la lista ya que por defect no me dejaria
    @OneToMany(mappedBy = "consult",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<ConsultDetail> details;

}
