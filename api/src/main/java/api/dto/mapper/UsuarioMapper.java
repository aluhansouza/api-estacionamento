package api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.dto.UsuarioCadastroDto;
import api.dto.UsuarioResponseDto;
import api.entity.Usuario;

@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Usuario paraUsuario(UsuarioCadastroDto cadastroDto) {
        return modelMapper.map(cadastroDto, Usuario.class);
    }

    public UsuarioResponseDto paraDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public List<UsuarioResponseDto> paraListDto(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::paraDto)
                .collect(Collectors.toList());
    }
}
