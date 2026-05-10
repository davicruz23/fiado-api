package com.flowtech.fiadoapi.controller;

import com.flowtech.fiadoapi.controller.mapper.CompanyMapper;
import com.flowtech.fiadoapi.model.Company;
import com.flowtech.fiadoapi.model.dto.company.CompanyDTO;
import com.flowtech.fiadoapi.model.dto.company.UpsertCompanyDTO;
import com.flowtech.fiadoapi.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService service;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(CompanyMapper.mapper(service.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(CompanyMapper::mapper).toList());
    }

    @GetMapping("/{id}/active")
    public ResponseEntity<CompanyDTO> findActiveById(@PathVariable Long id) {
        return ResponseEntity.ok().body(CompanyMapper.mapper(service.findActiveById(id)));
    }

    @GetMapping("/active/all")
    public ResponseEntity<List<CompanyDTO>> findAllActive() {
        return ResponseEntity.ok().body(service.findAllActive().stream().map(CompanyMapper::mapper).toList());
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> store(@RequestBody UpsertCompanyDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(service.register(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable Long id, @RequestBody UpsertCompanyDTO dto) {
        Company updated = service.update(id, dto);
        return ResponseEntity.ok().body(CompanyMapper.mapper(updated));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
