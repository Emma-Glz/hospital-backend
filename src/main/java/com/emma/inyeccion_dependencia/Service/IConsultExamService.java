package com.emma.inyeccion_dependencia.Service;

import com.emma.inyeccion_dependencia.Model.ConsultExam;

import java.util.List;

public interface IConsultExamService {
    List<ConsultExam> getExamsByConsultId(Integer idConsult);
}
