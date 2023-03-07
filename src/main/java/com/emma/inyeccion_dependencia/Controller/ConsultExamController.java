package com.emma.inyeccion_dependencia.Controller;

import com.emma.inyeccion_dependencia.Model.Consult;
import com.emma.inyeccion_dependencia.Model.ConsultExam;
import com.emma.inyeccion_dependencia.Service.imp.ConsultExamServiceImp;
import com.emma.inyeccion_dependencia.config.MapperConfig;
import com.emma.inyeccion_dependencia.dto.ConsultDTO;
import com.emma.inyeccion_dependencia.dto.ConsultExamDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/consultexams")
@RequiredArgsConstructor
public class ConsultExamController {
    private final ConsultExamServiceImp service;
    @Qualifier("consultMapper")
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<List<ConsultExamDTO>> searchByDates(@PathVariable("id") Integer id){
       List<ConsultExam> consultExams = service.getExamsByConsultId(id);
       List<ConsultExamDTO> dto = mapper.map(consultExams, new TypeToken<List<ConsultExamDTO>>(){}.getType());
    return new ResponseEntity<>(dto, OK);
    }
}
