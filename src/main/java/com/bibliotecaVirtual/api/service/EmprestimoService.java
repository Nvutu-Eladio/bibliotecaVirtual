package com.bibliotecaVirtual.api.service;

import com.bibliotecaVirtual.api.dto.request.EmprestimoRequestDTO;
import com.bibliotecaVirtual.api.dto.response.EmprestimoResponseDTO;
import com.bibliotecaVirtual.api.exception.EmprestimoNotFoundException;
import com.bibliotecaVirtual.api.exception.LivroIndisponivelException;
import com.bibliotecaVirtual.api.exception.LivroNotFoundException;
import com.bibliotecaVirtual.api.mapper.EmprestimoMapper;
import com.bibliotecaVirtual.api.model.Emprestimo;
import com.bibliotecaVirtual.api.model.Livro;
import com.bibliotecaVirtual.api.model.Usuario;
import com.bibliotecaVirtual.api.repository.EmprestimoRepository;
import com.bibliotecaVirtual.api.repository.LivroRepository;
import com.bibliotecaVirtual.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;


    public EmprestimoService(UsuarioRepository usuarioRepository, LivroRepository livroRepository, EmprestimoRepository emprestimoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public EmprestimoResponseDTO criar(EmprestimoRequestDTO requestDTO) throws LivroIndisponivelException {

        Livro livro = livroRepository.findById(requestDTO.getLivroId()).orElseThrow(()
                -> new LivroNotFoundException("Livro não encontrado")
        );

        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId()).orElseThrow(()
                -> new LivroNotFoundException("Usuário não encontrado")
        );

        if (!livro.getDisponivel()) {
            throw new LivroIndisponivelException("O livro não está disponível para empréstimo");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoEmprestimo(requestDTO.getDataDevolucaoEmprestimo());
        emprestimo.setStatus("Pendente");

        emprestimoRepository.save(emprestimo);

        return EmprestimoMapper.INSTANCE.convertEntityToListDto(emprestimo);
    }

    public List<EmprestimoResponseDTO> obterEmprestimo() {
        return EmprestimoMapper.INSTANCE.convertListEntityToListDto(emprestimoRepository.findAll());
    }

    public EmprestimoResponseDTO obterEmprestimoPorId(Long id) throws EmprestimoNotFoundException {

        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(()
                -> new EmprestimoNotFoundException("Emprestimo nao encontrado"));

        return EmprestimoMapper.INSTANCE.convertEntityToListDto(emprestimo);

    }

    public void apagar(Long id) throws EmprestimoNotFoundException {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(()
                -> new EmprestimoNotFoundException("Id informado nao encontrado"));

        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);

        emprestimoRepository.delete(emprestimo);
    }

}
