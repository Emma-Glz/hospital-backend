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
        TypeMap<MedicDTO, Medic> typeMap1 = mapper.createTypeMap(MedicDTO.class, Medic.class);
        typeMap1.addMapping(MedicDTO::getPrimaryName,(medic, o) -> medic.setFirstName((String) o));
        typeMap1.addMapping(MedicDTO::getSurName,(medic, o) -> medic.setLastName((String) o));
        typeMap1.addMapping(MedicDTO::getPhoto,(medic, o) -> medic.setPhotoUrl((String) o));

        TypeMap<Medic, MedicDTO> typeMap2 = mapper.createTypeMap(Medic.class, MedicDTO.class);
        typeMap2.addMapping(Medic::getFirstName,(medicDTO, o) -> medicDTO.setPrimaryName((String) o));
        typeMap2.addMapping(Medic::getLastName,(medicDTO, o) -> medicDTO.setSurName((String) o));
        return mapper;
    }
}
