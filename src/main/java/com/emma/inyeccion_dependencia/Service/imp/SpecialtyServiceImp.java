package com.emma.inyeccion_dependencia.Service.imp;

import com.emma.inyeccion_dependencia.Model.Specialty;
import com.emma.inyeccion_dependencia.Repo.IGenericRepo;
import com.emma.inyeccion_dependencia.Repo.ISpecialtyRepo;
import com.emma.inyeccion_dependencia.Service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImp extends CRUDImpl<Specialty, Integer> implements ISpecialtyService {
    private final ISpecialtyRepo repo;

    @Override
    protected IGenericRepo<Specialty, Integer> getRepo() {
        return repo;
    }

   /* @Override
    public Specialty save(Specialty patient) {
        return repo.save(patient);
    }

    @Override
    public Specialty update(Specialty patient) {
        return repo.save(patient);
    }

    @Override
    public List<Specialty> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Specialty> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);

    }*/



}

