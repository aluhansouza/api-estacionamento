package api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.entity.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Integer> {
	
	
	
	    Optional<Vaga> findByCodigo(String codigo);
	    
	    Optional<Vaga> findFirstByStatus(Vaga.StatusVaga statusVaga);
	

}