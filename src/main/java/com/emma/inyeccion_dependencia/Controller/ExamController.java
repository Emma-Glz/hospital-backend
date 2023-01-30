package com.emma.inyeccion_dependencia.Controller;

import com.emma.inyeccion_dependencia.Model.Exam;
import com.emma.inyeccion_dependencia.Service.imp.ExamServiceImp;
import com.emma.inyeccion_dependencia.dto.ExamDTO;
import com.emma.inyeccion_dependencia.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamServiceImp service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll(){
        List<ExamDTO> list = service.findAll().stream().map(this::converToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id){
        Exam obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
            //throw new NewModelNotFoundException("ID not found " + id);
        }
        return new ResponseEntity<>(converToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<ExamDTO> save(@Valid @RequestBody ExamDTO dto){
        Exam obj = service.save(converToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}").buildAndExpand(obj.getIdExam()).toUri();//para que agarre en el navegador
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@Valid @RequestBody ExamDTO dto,@PathVariable("id") Integer id ){
        Exam obj = service.update(converToEntity(dto),id);
        return new ResponseEntity<>(converToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delate(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    public ExamDTO converToDto(Exam obj){
        return mapper.map(obj,ExamDTO.class);
    }
    public Exam converToEntity(ExamDTO dto){
        return mapper.map(dto,Exam.class);
    }

}
