package br.com.LoginSpringBootThymeleaf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.LoginSpringBootThymeleaf.entities.GrupoEntity;
import br.com.LoginSpringBootThymeleaf.entities.UsuarioEntity;
 
@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Integer>{
 
	List<GrupoEntity> findByUsuarios(UsuarioEntity usuarioEntity);
 
}