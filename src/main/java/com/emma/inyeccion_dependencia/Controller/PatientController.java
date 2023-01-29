package com.emma.inyeccion_dependencia.Controller;

import com.emma.inyeccion_dependencia.Model.Patient;
import com.emma.inyeccion_dependencia.Service.imp.PatientServiceImp;
import com.emma.inyeccion_dependencia.dto.PatientDTO;
import com.emma.inyeccion_dependencia.exception.ModelNotFoundException;
import com.emma.inyeccion_dependencia.exception.NewModelNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientServiceImp service;
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll(){
        List<PatientDTO> list = service.findAll().stream().map(this::converToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
            //throw new NewModelNotFoundException("ID not found " + id);
        }
        return new ResponseEntity<>(converToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> save(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.save(converToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();//para que agarre en el navegador
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.update(converToEntity(dto));
        return new ResponseEntity<>(converToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delate(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    public PatientDTO converToDto(Patient obj){
        return mapper.map(obj,PatientDTO.class);
    }
    public Patient converToEntity(PatientDTO dto){
        return mapper.map(dto,Patient.class);
    }

}
