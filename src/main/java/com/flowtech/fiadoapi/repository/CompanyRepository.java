package com.flowtech.fiadoapi.repository;

import com.flowtech.fiadoapi.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCpf(String cpf);

    Optional<Company> findByIdAndActiveTrue(Long id);

    List<Company> findAllByActiveTrue();
}
