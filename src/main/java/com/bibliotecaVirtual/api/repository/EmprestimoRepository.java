package com.bibliotecaVirtual.api.repository;

import com.bibliotecaVirtual.api.model.Emprestimo;
import org.springframework.data.repository.CrudRepository;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {
}
