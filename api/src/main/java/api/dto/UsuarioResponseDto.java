package api.dto;

import api.entity.Perfil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioResponseDto {

    private int id;
    private String email;
    private PerfilResponseDto perfil;
    
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class PerfilResponseDto {
        private int id;
        private String nome;
    }
    
    
   
    
    
}


