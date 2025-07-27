package com.bibliotecaVirtual.api.service;

import com.bibliotecaVirtual.api.client.viacep.Endereco;
import com.bibliotecaVirtual.api.client.viacep.ViaCepClient;
import com.bibliotecaVirtual.api.dto.request.UsuarioRequestDTO;
import com.bibliotecaVirtual.api.dto.response.UsuarioResponse;
import com.bibliotecaVirtual.api.exception.UsuarioJaCadastradoException;
import com.bibliotecaVirtual.api.mapper.UsuarioMapper;
import com.bibliotecaVirtual.api.model.Usuario;
import com.bibliotecaVirtual.api.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    @Mock
    UsuarioRepository repository;

    @Mock
    ViaCepClient viaCepClient;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deve_lancar_excecao_quando_usuario_ja_cadastrado() {

        when(repository.findByNome("João")).thenReturn(new Usuario());

        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO();
        requestDTO.setNome("João");

        assertThrows(UsuarioJaCadastradoException.class, () -> usuarioService.criar(requestDTO));

        verify(repository, times(1)).findByNome("João");
    }

    @Test
    void deve_criar_usuario_com_cep_valido() throws UsuarioJaCadastradoException {

        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO();
        requestDTO.setNome("João");
        requestDTO.setNome("06020-194");

        when(repository.findByNome("João")).thenReturn(null);

        Endereco endereco = new Endereco("06020-112", "Avenida Dos Anjos", "Continental", "Pernambunco", "PE");
        when(viaCepClient.getEndereco("06020-194")).thenReturn(endereco);

        Usuario usuarioMock = new Usuario();
        usuarioMock.setNome("João");

        when(UsuarioMapper.INSTANCE.convertDtoToUsuario(requestDTO)).thenReturn(usuarioMock);

        when(repository.save(any(Usuario.class))).thenReturn(usuarioMock);

        UsuarioResponse response = usuarioService.criar(requestDTO);

        verify(repository, times(1)).findByNome("Maria");
        verify(viaCepClient, times(1)).getEndereco("01001-000");
        verify(repository, times(1)).save(any(Usuario.class));


    }




}