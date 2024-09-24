package api.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import api.entity.Perfil;
import api.entity.Usuario;
import api.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.buscarPorEmail(email);
        return new JwtUserDetails(usuario);
	}
	
	/*
	public JwtToken getTokenAuthenticated(String username) {
        Usuario.Role role = usuarioService.buscarRolePorUsername(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
    */
	
	public JwtToken getTokenAuthenticated(String email) {
	    Perfil perfil = usuarioService.buscarPerfilPorEmail(email);

	    if (perfil == null) {
	        throw new RuntimeException("Perfil não encontrado para o usuário: " + email);
	    }

	    // Presumindo que 'perfil' tem um método getNome() que retorna a role
	    String perfilNome = perfil.getNome();

	    // Cria o token JWT com o username e a role extraída
	    return JwtUtils.createToken(email, perfilNome);
	}

    
    
    
    
}
