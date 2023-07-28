package service.mapper;

import java.util.List;

public interface BaseMapper <E, D>{

    D toDto(E entity);

    List<D> toDtoList(List<E> entities);

    E toEntity(D dto);

    List<E> toEntityList(List<D> dtos);

}
