package br.com.LoginSpringBootThymeleaf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.LoginSpringBootThymeleaf.entities.GrupoEntity;
import br.com.LoginSpringBootThymeleaf.entities.PermissaoEntity;
 
@Repository
public interface PermissaoRepository extends JpaRepository<PermissaoEntity, Integer> {
 
	List<PermissaoEntity> findByGrupos(GrupoEntity grupoEntity);
}
