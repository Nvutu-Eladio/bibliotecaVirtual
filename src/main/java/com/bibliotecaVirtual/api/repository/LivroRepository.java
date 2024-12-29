package com.bibliotecaVirtual.api.repository;

import com.bibliotecaVirtual.api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

//    Optional<Livro> findById(String id);
}
