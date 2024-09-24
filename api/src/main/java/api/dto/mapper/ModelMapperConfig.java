package api.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.dto.UsuarioResponseDto;
import api.entity.Perfil;
import api.entity.Usuario;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        

        // Configurar o mapeamento para Perfil e PerfilResponseDto
        modelMapper.addMappings(new PropertyMap<Perfil, UsuarioResponseDto.PerfilResponseDto>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNome(source.getNome());
            }
        });

        return modelMapper;
    }
}