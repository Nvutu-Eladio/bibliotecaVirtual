package com.bibliotecaVirtual.api.repository;

import com.bibliotecaVirtual.api.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByNome(String nome);
}
