package com.flowtech.fiadoapi.service;

import com.flowtech.fiadoapi.exception.BusinessException;
import com.flowtech.fiadoapi.model.Company;
import com.flowtech.fiadoapi.model.dto.company.UpsertCompanyDTO;
import com.flowtech.fiadoapi.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final PasswordEncoder passwordEncoder;


    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findActiveById(Long id) {
        return repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada!"));
    }

    public List<Company> findAllActive() {
        return repository.findAllByActiveTrue();
    }

    public Company findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada!"));
    }

    public Company register(UpsertCompanyDTO dto) {
        if (repository.findByCpf(dto.getCpf()).isPresent()) {
            throw new BusinessException("O CPF já existe!");
        }

        Company company = new Company();
        company.setName(dto.getName());
        company.setCpf(dto.getCpf());
        company.setPassword(passwordEncoder.encode(dto.getPassword()));
        company.create();
        company = repository.save(company);

        return company;
    }

    public Company update(Long id, UpsertCompanyDTO dto) {
        Company existingCompany = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada!"));

        existingCompany.setName(dto.getName());

        return  repository.save(existingCompany);
    }

    public void delete(Long id) {
        Company company = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada!"));

        company.setActive(false);
        company.delete();
        repository.save(company);
    }
}
