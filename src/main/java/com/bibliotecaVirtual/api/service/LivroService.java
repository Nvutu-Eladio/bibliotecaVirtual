package com.bibliotecaVirtual.api.service;

import com.bibliotecaVirtual.api.dto.request.LivroRequestDTO;
import com.bibliotecaVirtual.api.dto.response.LivroResponseDTO;
import com.bibliotecaVirtual.api.exception.LivroJaCadastradoException;
import com.bibliotecaVirtual.api.exception.LivroNotFoundException;
import com.bibliotecaVirtual.api.mapper.LivroMapper;
import com.bibliotecaVirtual.api.model.Livro;
import com.bibliotecaVirtual.api.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public LivroResponseDTO criar(LivroRequestDTO requestDTO) {

        Livro livro = LivroMapper.INSTANCE.convertDtoToLivro(requestDTO);

        if ((livroRepository.findByTitulo(requestDTO.getTitulo()).isPresent())) {
            throw new LivroJaCadastradoException("O livro já cadastrado no banco de dados");
        }

        livroRepository.save(livro);

        return LivroMapper.INSTANCE.convertEntityToDto(livro);

    }


    public List<LivroResponseDTO> obterLivro() {
        return LivroMapper.INSTANCE.convertListEntityToListDto(livroRepository.findAll());
    }


    public LivroResponseDTO atualizar(String id, LivroRequestDTO requestDTO) {
        Livro livro = livroRepository.findById(Long.valueOf(id)).orElseThrow(() -> new LivroNotFoundException("Erro ao atualizar livro no banco de dados"));

        if (!requestDTO.getTitulo().isEmpty()) livro.setTitulo((requestDTO.getTitulo()));
        if (!requestDTO.getAutor().isEmpty()) livro.setAutor((requestDTO.getAutor()));
        if (!requestDTO.getGenero().isEmpty()) livro.setAutor((requestDTO.getAutor()));

        livroRepository.save(livro);

        return LivroMapper.INSTANCE.convertEntityToDto(livro);
    }


    public void apagar(String id) {
        Livro livro = this.livroRepository.findById(Long.valueOf(id)).orElseThrow(() -> new LivroNotFoundException("Livro não encontrado"));

        this.livroRepository.delete(livro);
    }


}
