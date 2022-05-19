package com.leords.client.services.mappers;

import com.leords.client.dto.ClientDTO;
import com.leords.client.entities.Client;

public class ClientMapper {
    
    public static Client dtoToEntity(ClientDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Client(
                dto.getId(),
                dto.getName(),
                dto.getCpf(),
                dto.getIncome(),
                dto.getBirthDate(),
                dto.getChildren()
        );
    }
    
    public static ClientDTO entityToDto(Client entity) {
        if (entity == null) {
            return null;
        }
        
        return new ClientDTO(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getIncome(),
                entity.getBirthDate(),
                entity.getChildren()
        );
    }
    
    public static void transformEntityToDto(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
    
}
