package api.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.entity.Perfil;
import api.entity.Usuario;
import api.exception.EntityNotFoundException;
import api.exception.PasswordInvalidException;
import api.exception.UsernameUniqueViolationException;
import api.repository.PerfilRepository;
import api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PerfilRepository perfilRepository;
	private final PasswordEncoder passwordEncoder;

	
	
	@Transactional
	public Usuario cadastrar(Usuario usuario, int perfilId) {
		try {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			
			Perfil perfil = perfilRepository.findById(perfilId)
	                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com ID: " + perfilId));
			
			usuario.setPerfil(perfil);
			
			
			return usuarioRepository.save(usuario);
		} catch (org.springframework.dao.DataIntegrityViolationException ex) {
			throw new UsernameUniqueViolationException(String.format("E-mail '%s' já cadastrado", usuario.getEmail()));
		}
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(int id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id)));
	}
	
	@Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario com '%s' não encontrado", email))
        );
    }
	

	@Transactional
    public Usuario alterarSenha(int id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new PasswordInvalidException("Nova senha não confere com confirmação de senha.");
        }

        Usuario usuario = buscarPorId(id);
        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new PasswordInvalidException("Sua senha não confere.");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        return usuario;
    }

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
	
	/*
	@Transactional(readOnly = true)
	public Perfil buscarPerfilPorEmail(String email) {
	    return usuarioRepository.findPerfilByEmail(email);
	}
	*/
	
	
	@Transactional(readOnly = true)
	public Perfil buscarPerfilPorEmail(String email) {
        Perfil perfil = usuarioRepository.findPerfilByEmail(email);
        if (perfil == null) {
            throw new RuntimeException("Perfil não encontrado para o usuário: " + email);
        }
        return perfil; // Retorna o nome do perfil
    }
	
	public void processarPerfil(String email) {
        Perfil perfil = buscarPerfilPorEmail(email);
        // Faça algo com o perfil
        System.out.println("Perfil encontrado: " + perfil);
    }
	

}