package com.emma.inyeccion_dependencia.Service;

import com.emma.inyeccion_dependencia.Model.Consult;
import com.emma.inyeccion_dependencia.Model.Exam;

import java.util.List;

public interface IConsultService extends ICRUD<Consult,Integer> {

    Consult saveTransactional(Consult consult, List<Exam> exams);
}
