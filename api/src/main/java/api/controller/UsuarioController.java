
package api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.dto.UsuarioCadastroDto;
import api.dto.UsuarioResponseDto;
import api.dto.UsuarioSenhaDto;
import api.dto.mapper.UsuarioMapper;
import api.entity.Usuario;
import api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(@Valid @RequestBody UsuarioCadastroDto cadastroDto) {
        Usuario usuario = usuarioService.cadastrar(UsuarioMapper.paraUsuario(cadastroDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.paraDto(usuario));
    }
	

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.paraDto(usuario));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> alterarSenha(@PathVariable int id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.alterarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.paraListDto(usuarios));
    }
    

}
