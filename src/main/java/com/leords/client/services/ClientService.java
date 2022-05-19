package com.leords.client.services;

import com.leords.client.dto.ClientDTO;
import com.leords.client.repositories.ClientRepository;
import com.leords.client.services.exceptions.ClientNotFoundException;
import com.leords.client.services.exceptions.DatabaseException;
import com.leords.client.services.mappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;
    
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllClient(PageRequest pageRequest) {
        var clientList = repository.findAll(pageRequest);
        return clientList.map(ClientMapper::entityToDto);
    }
    
    @Transactional(readOnly = true)
    public ClientDTO findClientById(Long id) {
        var client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return ClientMapper.entityToDto(client);
    }
    
    @Transactional
    public ClientDTO createClient(ClientDTO dto) {
        var client = ClientMapper.dtoToEntity(dto);
        client = repository.save(client);
        return ClientMapper.entityToDto(client);
    }
    
    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        try {
            var client = repository.getOne(id);
            ClientMapper.transformEntityToDto(dto, client);
            return ClientMapper.entityToDto(client);
        } catch (EntityNotFoundException error) {
            throw new ClientNotFoundException("Client with " + id + " not found");
        }
    }
    
    public void deleteClientById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ClientNotFoundException("Client with " + id + " not found");
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException("Integrity violation");
        }
    }
    
}
