package com.emma.inyeccion_dependencia.Repo;

import com.emma.inyeccion_dependencia.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepo extends IGenericRepo<Patient,Integer> {
}
