package com.emma.inyeccion_dependencia.Controller;

import com.emma.inyeccion_dependencia.Model.Specialty;
import com.emma.inyeccion_dependencia.Service.imp.SpecialtyServiceImp;
import com.emma.inyeccion_dependencia.dto.SpecialtyDTO;
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
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyServiceImp service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> findAll(){
        List<SpecialtyDTO> list = service.findAll().stream().map(this::converToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable("id") Integer id){
        Specialty obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
            //throw new NewModelNotFoundException("ID not found " + id);
        }
        return new ResponseEntity<>(converToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> save(@Valid @RequestBody SpecialtyDTO dto){
        Specialty obj = service.save(converToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}").buildAndExpand(obj.getIdSpecialty()).toUri();//para que agarre en el navegador
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@Valid @RequestBody SpecialtyDTO dto,@PathVariable("id") Integer id ){
        Specialty obj = service.update(converToEntity(dto),id);
        return new ResponseEntity<>(converToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delate(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    public SpecialtyDTO converToDto(Specialty obj){
        return mapper.map(obj,SpecialtyDTO.class);
    }
    public Specialty converToEntity(SpecialtyDTO dto){
        return mapper.map(dto,Specialty.class);
    }

}
