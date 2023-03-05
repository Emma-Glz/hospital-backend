package com.emma.inyeccion_dependencia.config;

import com.emma.inyeccion_dependencia.Model.Consult;
import com.emma.inyeccion_dependencia.Model.Medic;
import com.emma.inyeccion_dependencia.dto.ConsultDTO;
import com.emma.inyeccion_dependencia.dto.MedicDTO;
import org.hibernate.collection.spi.PersistentCollection;
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

    @Bean("consultMapper")
    public ModelMapper consultMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<Consult, ConsultDTO> typeMap1 = mapper.createTypeMap(Consult.class, ConsultDTO.class);

        typeMap1.addMapping(e -> e.getMedic().getFirstName(),(dest, v) -> dest.getMedic().setPrimaryName((String) v) );
        typeMap1.addMapping(e -> e.getMedic().getLastName(),(dest, v) -> dest.getMedic().setSurName((String) v) );
        typeMap1.addMapping(e -> e.getMedic().getPhotoUrl(),(dest, v) -> dest.getMedic().setPhoto((String) v) );
        //using default method lazy
        //mapper.getConfiguration().setPropertyCondition(context  -> !(context.getSource() instanceof PersistentCollection));
        return mapper;
    }
}
