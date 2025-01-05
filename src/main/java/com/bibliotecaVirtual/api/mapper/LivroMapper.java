package com.bibliotecaVirtual.api.mapper;

import com.bibliotecaVirtual.api.dto.request.LivroRequestDTO;
import com.bibliotecaVirtual.api.dto.response.LivroResponseDTO;
import com.bibliotecaVirtual.api.dto.LivroDTO;
import com.bibliotecaVirtual.api.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LivroMapper {

    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    @Mapping(target = "titulo", source = "titulo")
    @Mapping(target = "autor", source = "autor")
    @Mapping(target = "genero", source = "genero")
    @Mapping(target = "disponivel", source = "disponivel")
    Livro convertDtoToLivro(LivroRequestDTO livroRequestDTO);

    @Mapping(target = "titulo", source = "titulo")
    @Mapping(target = "autor", source = "autor")
    @Mapping(target = "genero", source = "genero")
    @Mapping(target = "disponivel", source = "disponivel")
    LivroResponseDTO convertEntityToDto(Livro livro);

    List<LivroResponseDTO> convertListEntityToListDto(Iterable<Livro> livros);

}
