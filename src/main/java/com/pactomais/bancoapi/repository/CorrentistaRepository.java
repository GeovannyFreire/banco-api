package com.pactomais.bancoapi.repository;

import com.pactomais.bancoapi.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
}