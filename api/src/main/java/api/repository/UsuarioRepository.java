package api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.entity.Perfil;
import api.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	
	Optional<Usuario> findByEmail(String email);
	
	Perfil findPerfilByEmail(String email);

}
