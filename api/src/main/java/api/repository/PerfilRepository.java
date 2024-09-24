package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
	
	
	

}
