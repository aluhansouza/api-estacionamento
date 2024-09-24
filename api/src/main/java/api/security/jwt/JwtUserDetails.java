package api.security.jwt;

import api.entity.Usuario;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private Usuario usuario;

    public JwtUserDetails(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), AuthorityUtils.createAuthorityList(usuario.getPerfil().getNome()));
        this.usuario = usuario;
    }

    public Integer getId() {
        return this.usuario.getId();
    }

    public String getPerfil() {
        return this.usuario.getPerfil().getNome();
    }
}
