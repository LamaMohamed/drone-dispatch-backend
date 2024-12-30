package com.elmenus.drones.shared.mapper;

public interface EntityMapper<E, D> {

    E toEntity(D objectDTO, Class<? extends E> entityClass);

    D toDTO(E entity, Class<? extends D> dtoClass);
}
