package com.leords.client.resources;

import com.leords.client.dto.ClientDTO;
import com.leords.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
    
    @Autowired
    private ClientService service;
    
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAllClient(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        var clientsDtoList = service.findAllClient(pageRequest);
        return ResponseEntity.ok().body(clientsDtoList);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable(value = "id") Long id) {
        var clientDto = service.findClientById(id);
        return ResponseEntity.ok().body(clientDto);
    }
    
}
