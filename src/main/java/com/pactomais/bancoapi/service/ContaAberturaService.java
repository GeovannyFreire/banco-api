package com.pactomais.bancoapi.service;

import com.pactomais.bancoapi.model.Conta;
import com.pactomais.bancoapi.model.Correntista;
import com.pactomais.bancoapi.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ContaAberturaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CorrentistaService correntistaService;

    public Conta abrir(Conta conta, Long correntistaId) {
        Correntista correntista = correntistaService.buscarPorId(correntistaId);
        conta.setCorrentista(correntista);
        conta.setSaldo(BigDecimal.ZERO);
        conta.setNumeroConta(UUID.randomUUID().toString().substring(0, 8));
        conta.setAgencia("0001");
        return contaRepository.save(conta);
    }

    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
}