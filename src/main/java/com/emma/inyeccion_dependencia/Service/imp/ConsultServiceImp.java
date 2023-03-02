package com.emma.inyeccion_dependencia.Service.imp;

import com.emma.inyeccion_dependencia.Model.Consult;
import com.emma.inyeccion_dependencia.Model.Exam;
import com.emma.inyeccion_dependencia.Repo.IConsultRepo;
import com.emma.inyeccion_dependencia.Repo.IGenericRepo;
import com.emma.inyeccion_dependencia.Repo.IConsultExamRepo;
import com.emma.inyeccion_dependencia.Service.IConsultService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultServiceImp extends CRUDImpl<Consult, Integer> implements IConsultService {
    private final IConsultRepo consultrepo;
    private final IConsultExamRepo ceRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultrepo;
    }
    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        consultrepo.save(consult);
        exams.forEach(exam -> ceRepo.saveExam(consult.getIdConsult(),exam.getIdExam()));
        return consult;
    }
}

