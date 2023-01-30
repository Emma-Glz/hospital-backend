package com.emma.inyeccion_dependencia.Controller;

import com.emma.inyeccion_dependencia.Model.Medic;
import com.emma.inyeccion_dependencia.Service.imp.MedicServiceImp;
import com.emma.inyeccion_dependencia.dto.MedicDTO;
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
@RequestMapping("/medics")
@RequiredArgsConstructor
public class MedicController {

    private final MedicServiceImp service;
    @Qualifier("medicMapper")
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll(){
        List<MedicDTO> list = service.findAll().stream().map(this::converToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id){
        Medic obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
            //throw new NewModelNotFoundException("ID not found " + id);
        }
        return new ResponseEntity<>(converToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<MedicDTO> save(@Valid @RequestBody MedicDTO dto){
        Medic obj = service.save(converToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();//para que agarre en el navegador
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@Valid @RequestBody MedicDTO dto,@PathVariable("id") Integer id ){
        Medic obj = service.update(converToEntity(dto),id);
        return new ResponseEntity<>(converToDto(obj),OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delate(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    public MedicDTO converToDto(Medic obj){
        return mapper.map(obj,MedicDTO.class);
    }
    public Medic converToEntity(MedicDTO dto){
        return mapper.map(dto,Medic.class);
    }

}
