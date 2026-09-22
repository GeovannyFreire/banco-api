package com.pactomais.bancoapi.repository;

import com.pactomais.bancoapi.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}