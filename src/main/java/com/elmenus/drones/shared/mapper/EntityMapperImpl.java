package com.elmenus.drones.shared.mapper;

import com.elmenus.drones.model.dto.DroneDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EntityMapperImpl<E, D> implements  EntityMapper<E, D>{

    private final ModelMapper modelMapper;

    @Override
    public E toEntity(D objectDTO, Class<? extends E> entityClass) {
        return modelMapper.map(objectDTO, entityClass);
    }

    @Override
    public D toDTO(E entity, Class<? extends D> dtoClass) {
        return  modelMapper.map(entity, dtoClass);
    }
}
