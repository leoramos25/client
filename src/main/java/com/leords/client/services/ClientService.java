package com.leords.client.services;

import com.leords.client.dto.ClientDTO;
import com.leords.client.repositories.ClientRepository;
import com.leords.client.services.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;
    
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllClient(PageRequest pageRequest) {
        var clientList = repository.findAll(pageRequest);
        return clientList.map(ClientDTO::new);
    }
    
    @Transactional(readOnly = true)
    public ClientDTO findClientById(Long id) {
        var client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return new ClientDTO(client);
    }
    
}
