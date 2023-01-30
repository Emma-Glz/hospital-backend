package com.emma.inyeccion_dependencia.Repo;

import com.emma.inyeccion_dependencia.Model.Patient;
import com.emma.inyeccion_dependencia.Model.Specialty;
import com.emma.inyeccion_dependencia.dto.SpecialtyDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialtyRepo extends IGenericRepo<Specialty,Integer> {
}
