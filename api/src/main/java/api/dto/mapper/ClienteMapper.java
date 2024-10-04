package api.dto.mapper;

import org.modelmapper.ModelMapper;

import api.dto.ClienteCadastroDto;

import api.dto.ClienteResponseDto;
import api.entity.Cliente;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente toCliente(ClienteCadastroDto dto) {
        return new ModelMapper().map(dto, Cliente.class);
    }

    public static ClienteResponseDto toDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
    
    
}
