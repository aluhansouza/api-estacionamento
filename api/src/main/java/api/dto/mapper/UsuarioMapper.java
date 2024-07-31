package api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import api.dto.UsuarioCadastroDto;
import api.dto.UsuarioResponseDto;
import api.entity.Usuario;

public class UsuarioMapper {

    public static Usuario paraUsuario(UsuarioCadastroDto cadastroDto) {
        return new ModelMapper().map(cadastroDto, Usuario.class);
    }

    public static UsuarioResponseDto paraDto(Usuario usuario) {
       
        ModelMapper mapper = new ModelMapper();
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> paraListDto(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> paraDto(usuario)).collect(Collectors.toList());
    }
}
