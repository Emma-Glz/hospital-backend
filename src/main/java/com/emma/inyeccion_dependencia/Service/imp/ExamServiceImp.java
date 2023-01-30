package com.emma.inyeccion_dependencia.Service.imp;

import com.emma.inyeccion_dependencia.Model.Exam;
import com.emma.inyeccion_dependencia.Repo.IGenericRepo;
import com.emma.inyeccion_dependencia.Repo.IExamRepo;
import com.emma.inyeccion_dependencia.Service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImp extends CRUDImpl<Exam, Integer> implements IExamService {
    private final IExamRepo repo;

    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }

   /* @Override
    public Exam save(Exam patient) {
        return repo.save(patient);
    }

    @Override
    public Exam update(Exam patient) {
        return repo.save(patient);
    }

    @Override
    public List<Exam> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Exam> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);

    }*/



}

