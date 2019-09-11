package pl.piotrschodzinski.homebudget.mapper;

public interface DtoMapper<E, D> {
    E mapToEntity(D dtoObject);

    D mapToDto(E entityObject);
}
