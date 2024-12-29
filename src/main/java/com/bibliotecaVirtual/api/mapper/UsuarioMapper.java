package com.bibliotecaVirtual.api.mapper;

import com.bibliotecaVirtual.api.dto.request.UsuarioRequestDTO;
import com.bibliotecaVirtual.api.dto.response.UsuarioResponseDTO;
import com.bibliotecaVirtual.api.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "bairro", source = "bairro")
    @Mapping(target = "rua", source = "rua")
    @Mapping(target = "cep", source = "cep")
    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "complemento", source = "complemento")
    @Mapping(target = "tipo", source = "tipo")
    Usuario convertDtoToUsuario(UsuarioRequestDTO usuarioRequestDTO);


    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "bairro", source = "bairro")
    @Mapping(target = "rua", source = "rua")
    @Mapping(target = "cep", source = "cep")
    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "complemento", source = "complemento")
    @Mapping(target = "tipo", source = "tipo")
    UsuarioResponseDTO convertEntityToDto(Usuario usuario);

    List<UsuarioResponseDTO> convertListEntityToList(Iterable<Usuario> usuarios);
}
