package com.bibliotecaVirtual.api.mapper;

import com.bibliotecaVirtual.api.dto.response.EmprestimoResponseDTO;
import com.bibliotecaVirtual.api.model.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmprestimoMapper {

    EmprestimoMapper INSTANCE = Mappers.getMapper(EmprestimoMapper.class);


    @Mapping(target = "emprestimoId", source = "emprestimoId")
    @Mapping(target = "dataEmprestimo", source = "dataEmprestimo")
    @Mapping(target = "dataDevolucaoEmprestimo", source = "dataDevolucaoEmprestimo")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "usuarioId", source = "emprestimo.usuario.usuarioId")
    @Mapping(target = "nome", source = "emprestimo.usuario.nome")
    @Mapping(target = "livroId", source = "emprestimo.livro.livroId")
    @Mapping(target = "titulo", source = "emprestimo.livro.titulo")
    EmprestimoResponseDTO convertEntityToListDto(Emprestimo emprestimo);

    List<EmprestimoResponseDTO> convertListEntityToListDto(Iterable<Emprestimo> emprestimos);

}
