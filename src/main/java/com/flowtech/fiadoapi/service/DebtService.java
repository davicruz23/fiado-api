package com.flowtech.fiadoapi.service;

import com.flowtech.fiadoapi.model.Client;
import com.flowtech.fiadoapi.model.Debt;
import com.flowtech.fiadoapi.model.dto.debt.UpsertDebtDTO;
import com.flowtech.fiadoapi.model.dto.user.UpsertUserDTO;
import com.flowtech.fiadoapi.repository.DebtRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtService {

    private final DebtRepository repository;
    private final ModelMapper mapper;


    public List<Debt> findAll() {
        return repository.findAll();
    }

    public Debt findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Debito não encontrado!"));
    }

    public Debt create(UpsertDebtDTO dto) {
        return repository.save(mapper.map(dto, Debt.class));
    }

    public Debt update(Long id, UpsertDebtDTO dto) {
        Debt existingDebt = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Debito não encontrado!"));
        existingDebt.setAmount(dto.getAmount());

        return  repository.save(existingDebt);
    }

    public void delete(Long id) {
        Debt debt = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Debito não encontrado!"));

        debt.delete();
        repository.save(debt);
    }
}
