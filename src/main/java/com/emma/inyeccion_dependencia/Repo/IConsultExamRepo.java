package com.emma.inyeccion_dependencia.Repo;

import com.emma.inyeccion_dependencia.Model.ConsultExam;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IConsultExamRepo extends IGenericRepo<ConsultExam, Integer> {
//    @Transactional
    @Modifying
    @Query(value = "INSERT INTO consult_exam(id_consult,id_exam) VALUES(:idConsult, :idExam)", nativeQuery = true)
            Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);
}
