package com.emma.inyeccion_dependencia.Repo;

import com.emma.inyeccion_dependencia.Model.Exam;
import org.springframework.stereotype.Repository;

@Repository
public interface IExamRepo extends IGenericRepo<Exam,Integer> {
}
