package com.pactomais.bancoapi.repository;

import com.pactomais.bancoapi.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}