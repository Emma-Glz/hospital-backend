package com.emma.inyeccion_dependencia.Service.imp;

import com.emma.inyeccion_dependencia.Model.Medic;
import com.emma.inyeccion_dependencia.Model.Patient;
import com.emma.inyeccion_dependencia.Repo.IGenericRepo;
import com.emma.inyeccion_dependencia.Repo.IMedicRepo;
import com.emma.inyeccion_dependencia.Repo.IPatientRepo;
import com.emma.inyeccion_dependencia.Service.IMedicService;
import com.emma.inyeccion_dependencia.Service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicServiceImp extends CRUDImpl<Medic, Integer> implements IMedicService {
    private final IMedicRepo repo;

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }

   /* @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Patient> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);

    }*/



}

