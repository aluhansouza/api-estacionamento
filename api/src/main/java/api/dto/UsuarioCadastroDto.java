package api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class UsuarioCadastroDto {
	
	
	// Adicionando o construtor que aceita dois parâmetros
    public UsuarioCadastroDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
	
	@NotBlank
    @Email(message = "formato do e-mail está invalido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;
    
    
    
	@NotBlank
    @Size(min = 8, max = 20)
    private String senha;
	
	@NotNull
    private int perfil;
	
	
}
