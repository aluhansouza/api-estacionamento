package api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.entity.Usuario;
import api.exception.EntityNotFoundException;
import api.exception.UsernameUniqueViolationException;
import api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario cadastrar(Usuario usuario) {
		 try {
	            return usuarioRepository.save(usuario);
	        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
	            throw new UsernameUniqueViolationException(String.format("E-mail '%s' já cadastrado", usuario.getEmail()));
	        }
	}

	 @Transactional(readOnly = true)
	    public Usuario buscarPorId(int id) {
	        return usuarioRepository.findById(id).orElseThrow(
	                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id))
	        );
	    }
	
	 @Transactional
	    public Usuario alterarSenha(int id, String senhaAtual, String novaSenha, String confirmaSenha) {
	        if (!novaSenha.equals(confirmaSenha)) {
	            throw new RuntimeException("Nova senha não confere com confirmação de senha.");
	        }

	        Usuario usuario = buscarPorId(id);
	        if (!usuario.getSenha().equals(senhaAtual)) {
	            throw new RuntimeException("Sua senha não confere.");
	        }

	        usuario.setSenha(novaSenha);
	        return usuario;
	    }

	    @Transactional(readOnly = true)
	    public List<Usuario> buscarTodos() {
	        return usuarioRepository.findAll();
	    }

}