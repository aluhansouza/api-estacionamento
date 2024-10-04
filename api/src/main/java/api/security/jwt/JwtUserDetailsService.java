package api.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Usuario usuario = usuarioService.buscarPorEmail(email);
//        return new JwtUserDetails(usuario);
//	}
    
    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Usuario usuario = usuarioService.buscarPorEmail(email);
	    if (usuario == null) {
	        throw new UsernameNotFoundException("Usuário não encontrado: " + email);
	    }
	    JwtUserDetails userDetails = new JwtUserDetails(usuario);
	    // Logando os detalhes do usuário e os papéis
	    
	    logger.info("Perfil do usuário: {}", usuario.getPerfil().getNome()); // Log do nome do perfil
	    System.out.println("Usuário: " + userDetails.getUsername() + ", Papéis: " + userDetails.getAuthorities());
	    return userDetails;
	}
	


	
	
	public JwtToken getTokenAuthenticated(String email) {
	    Perfil perfil = usuarioService.buscarPerfilPorEmail(email);

	    if (perfil == null) {
	        throw new RuntimeException("Perfil não encontrado para o usuário: " + email);
	    }

	    // Cria o token JWT com o username e a role extraída
	    return JwtUtils.createToken(email, "ROLE_" + perfil.getNome());
	}
    
    
    
    
}
