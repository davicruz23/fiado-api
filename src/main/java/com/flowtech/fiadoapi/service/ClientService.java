package com.flowtech.fiadoapi.service;

import com.flowtech.fiadoapi.model.Client;
import com.flowtech.fiadoapi.model.dto.client.UpsertClientDTO;
import com.flowtech.fiadoapi.model.dto.user.UpsertUserDTO;
import com.flowtech.fiadoapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ModelMapper mapper;


    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public Client register(UpsertClientDTO dto) {
        return repository.save(mapper.map(dto, Client.class));
    }

    public Client update(Long id, UpsertClientDTO dto) {
        Client existingCompany = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        existingCompany.setName(dto.getName());

        return  repository.save(existingCompany);
    }

    public void delete(Long id) {
        Client Client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        Client.delete();
        repository.save(Client);
    }
}
