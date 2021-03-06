package br.com.LoginSpringBootThymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.LoginSpringBootThymeleaf.entities.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	 
	UsuarioEntity findByLogin(String login);
 
}
