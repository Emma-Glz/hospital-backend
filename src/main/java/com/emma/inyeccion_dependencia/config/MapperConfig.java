package com.emma.inyeccion_dependencia.config;

import com.emma.inyeccion_dependencia.Model.Medic;
import com.emma.inyeccion_dependencia.dto.MedicDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {
    @Bean("defaultMapper")
    //@Primary
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean("medicMapper")
    public ModelMapper medicMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<MedicDTO, Medic> typeMap = mapper.createTypeMap(MedicDTO.class, Medic.class);
        typeMap.addMapping(MedicDTO::getPrimaryName,(medic, o) -> medic.setFirstName((String) o));
        typeMap.addMapping(MedicDTO::getSurName,(medic, o) -> medic.setLastName((String) o));
        typeMap.addMapping(MedicDTO::getPhoto,(medic, o) -> medic.setPhotoUrl((String) o));
        return mapper;
    }
}
