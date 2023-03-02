package com.emma.inyeccion_dependencia.Controller;

import com.emma.inyeccion_dependencia.Model.Consult;
import com.emma.inyeccion_dependencia.Model.Exam;
import com.emma.inyeccion_dependencia.Service.imp.ConsultServiceImp;
import com.emma.inyeccion_dependencia.dto.ConsultDTO;
import com.emma.inyeccion_dependencia.dto.ConsultListExamDTO;
import com.emma.inyeccion_dependencia.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {

    private final ConsultServiceImp service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll(){
        List<ConsultDTO> list = service.findAll().stream().map(this::converToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id){
        Consult obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
            //throw new NewModelNotFoundException("ID not found " + id);
        }
        return new ResponseEntity<>(converToDto(obj), OK);
    }
    @PostMapping
    public ResponseEntity<ConsultDTO> save(@Valid @RequestBody ConsultListExamDTO dto){
        Consult cons = this.converToEntity(dto.getConsult());
        List<Exam> exams = mapper.map(dto.getLstExam(),new TypeToken<List<Exam>>(){}.getType());//typeToken permite convertir de obj a lista

        Consult obj = service.saveTransactional(cons,exams);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();//para que agarre en el navegador
        return ResponseEntity.created(location).build();
    }
   /* @PostMapping
    public ResponseEntity<ConsultDTO> save(@Valid @RequestBody ConsultDTO dto){
        Consult obj = service.save(converToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();//para que agarre en el navegador
        return ResponseEntity.created(location).build();
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@Valid @RequestBody ConsultDTO dto,@PathVariable("id") Integer id ){
        Consult obj = service.update(converToEntity(dto),id);
        return new ResponseEntity<>(converToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delate(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    public ConsultDTO converToDto(Consult obj){
        return mapper.map(obj,ConsultDTO.class);
    }
    public Consult converToEntity(ConsultDTO dto){
        return mapper.map(dto,Consult.class);
    }

}
