package com.bibliotecaVirtual.api.service;

import com.bibliotecaVirtual.api.client.viacep.ViaCepClient;
import com.bibliotecaVirtual.api.dto.request.UsuarioRequestDTO;
import com.bibliotecaVirtual.api.dto.response.UsuarioResponse;
import com.bibliotecaVirtual.api.exception.UsuarioJaCadastradoException;
import com.bibliotecaVirtual.api.exception.UsuarioNotFoundException;
import com.bibliotecaVirtual.api.mapper.UsuarioMapper;
import com.bibliotecaVirtual.api.model.Usuario;
import com.bibliotecaVirtual.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final ViaCepClient viaCepClient;

    public UsuarioService(UsuarioRepository usuarioRepository, ViaCepClient viaCepClient) {
        this.usuarioRepository = usuarioRepository;
        this.viaCepClient = viaCepClient;
    }

    public UsuarioResponse criar(UsuarioRequestDTO requestDTO) throws UsuarioJaCadastradoException {

        if ((usuarioRepository.findByNome(requestDTO.getNome()) != null)) {
            throw new UsuarioJaCadastradoException("Usuario já cadastrado no banco de dados");
        }

        if (requestDTO.getCep() != null){
            var endereco = viaCepClient.getEndereco(requestDTO.getCep());

            requestDTO.setEstado(endereco.uf());
            requestDTO.setCidade(endereco.localidade());
            requestDTO.setBairro(endereco.bairro());
            requestDTO.setRua(endereco.logradouro());

        }

        Usuario usuario = UsuarioMapper.INSTANCE.convertDtoToUsuario(requestDTO);

        usuarioRepository.save(usuario);

        return UsuarioMapper.INSTANCE.convertEntityToDto(usuario);

    }

  public List<UsuarioResponse> obterUsuario(){
        return UsuarioMapper.INSTANCE.convertListEntityToList(usuarioRepository.findAll());
  }

  public UsuarioResponse obterUsuarioPorId(Long id) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
                -> new UsuarioNotFoundException("Usuario não encontrado"));

        return UsuarioMapper.INSTANCE.convertEntityToDto(usuario);
  }

  public UsuarioResponse atualizar(Long id, UsuarioRequestDTO requestDTO) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
                -> new UsuarioNotFoundException("Erro ao atualizar usuario no banco de dados"));

        if (!requestDTO.getNome().isEmpty()) usuario.setNome((requestDTO.getNome()));
        if (!requestDTO.getEmail().isEmpty()) usuario.setEmail((requestDTO.getEmail()));
        if (!requestDTO.getEstado().isEmpty()) usuario.setEstado((requestDTO.getEstado()));
        if (!requestDTO.getCidade().isEmpty()) usuario.setCidade((requestDTO.getCidade()));
        if (!requestDTO.getBairro().isEmpty()) usuario.setBairro((requestDTO.getBairro()));
        if (!requestDTO.getRua().isEmpty()) usuario.setRua((requestDTO.getRua()));
        if (!requestDTO.getCep().isEmpty()) usuario.setCep((requestDTO.getCep()));
        if (!requestDTO.getNumero().isEmpty()) usuario.setNumero((requestDTO.getNumero()));
        if (!requestDTO.getComplemento().isEmpty()) usuario.setComplemento((requestDTO.getComplemento()));
        if (!requestDTO.getTipo().isEmpty()) usuario.setTipo((requestDTO.getTipo()));

        usuarioRepository.save(usuario);

        return UsuarioMapper.INSTANCE.convertEntityToDto(usuario);
  }

  public void apagar(Long id) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
                -> new UsuarioNotFoundException("Usuario nao encontrado"));

        usuarioRepository.delete(usuario);
  }
}
