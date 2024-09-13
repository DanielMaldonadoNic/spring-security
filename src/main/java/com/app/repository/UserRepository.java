package com.app.repository;

import com.app.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findUsuarioEntityByUsername(String username);
}
