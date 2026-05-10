package com.flowtech.fiadoapi.controller;

import com.flowtech.fiadoapi.controller.mapper.ClientMapper;
import com.flowtech.fiadoapi.model.Client;
import com.flowtech.fiadoapi.model.dto.client.ClientDTO;
import com.flowtech.fiadoapi.model.dto.client.UpsertClientDTO;
import com.flowtech.fiadoapi.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {

    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ClientMapper.toDTO(service.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(ClientMapper::toDTO).toList());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> store(@RequestBody UpsertClientDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(service.register(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody UpsertClientDTO dto) {
        Client updated = service.update(id, dto);
        return ResponseEntity.ok().body(ClientMapper.toDTO(updated));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
