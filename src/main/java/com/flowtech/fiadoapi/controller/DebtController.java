package com.flowtech.fiadoapi.controller;

import com.flowtech.fiadoapi.controller.mapper.DebtMapper;
import com.flowtech.fiadoapi.model.Debt;
import com.flowtech.fiadoapi.model.dto.debt.DebtDTO;
import com.flowtech.fiadoapi.model.dto.debt.UpsertDebtDTO;
import com.flowtech.fiadoapi.service.DebtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/debt")
public class DebtController {

    private DebtService service;

    @GetMapping("/{id}")
    public ResponseEntity<DebtDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(DebtMapper.toDTO(service.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DebtDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(DebtMapper::toDTO).toList());
    }

    @PostMapping
    public ResponseEntity<DebtDTO> store(@RequestBody UpsertDebtDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DebtDTO> update(@PathVariable Long id, @RequestBody UpsertDebtDTO dto) {
        Debt updated = service.update(id, dto);
        return ResponseEntity.ok().body(DebtMapper.toDTO(updated));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
