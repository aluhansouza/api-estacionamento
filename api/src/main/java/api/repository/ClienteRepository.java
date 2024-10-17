package api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.entity.Cliente;
import api.repository.projection.ClienteProjection;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c")
    Page<ClienteProjection> findAllPageable(Pageable pageable);

    Cliente findByUsuarioId(Integer id);
    
    Optional<Cliente> findByCpf(String cpf);
    
}
