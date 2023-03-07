package com.emma.inyeccion_dependencia.Controller;

import com.emma.inyeccion_dependencia.Model.Consult;
import com.emma.inyeccion_dependencia.Model.Exam;
import com.emma.inyeccion_dependencia.Service.imp.ConsultServiceImp;
import com.emma.inyeccion_dependencia.dto.ConsultDTO;
import com.emma.inyeccion_dependencia.dto.ConsultListExamDTO;
import com.emma.inyeccion_dependencia.dto.FilterConsultDTO;
import com.emma.inyeccion_dependencia.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
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

    @Qualifier("consultMapper")
    private final ModelMapper consultMapper;
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

    @PostMapping("/search/others")
    public ResponseEntity<List<ConsultDTO>> searchByOthers(@RequestBody FilterConsultDTO filterdto){
        List<Consult> consults = service.search(filterdto.getDni(), filterdto.getFullname());
        List<ConsultDTO> consultsDTO = consultMapper.map(consults, new TypeToken<List<ConsultDTO>>(){}.getType());
        return new ResponseEntity<>(consultsDTO, OK);
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<ConsultDTO>> searchByDates(@RequestParam(value = "date1") String date1, @RequestParam(value = "date2") String date2 ){
        List<Consult> consults =  service.searchByDates(LocalDateTime.parse(date1), LocalDateTime.parse(date2));
        List<ConsultDTO> consultsDTO = consultMapper.map(consults, new TypeToken<List<ConsultDTO>>(){}.getType());
        return new ResponseEntity<>(consultsDTO, OK);
    }

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
