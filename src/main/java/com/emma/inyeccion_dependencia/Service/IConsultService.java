package com.emma.inyeccion_dependencia.Service;

import com.emma.inyeccion_dependencia.Model.Consult;
import com.emma.inyeccion_dependencia.Model.Exam;
import com.emma.inyeccion_dependencia.Model.Patient;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IConsultService extends ICRUD<Consult,Integer> {

    Consult saveTransactional(Consult consult, List<Exam> exams);
    List<Consult> search(String dni, String fullname);
    List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2 );
}
