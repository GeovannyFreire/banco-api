package com.pactomais.bancoapi.service;

import com.pactomais.bancoapi.model.Conta;
import com.pactomais.bancoapi.model.Transacao;
import com.pactomais.bancoapi.model.TipoTransacao;
import com.pactomais.bancoapi.repository.ContaRepository;
import com.pactomais.bancoapi.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public Conta depositar(Long contaId, BigDecimal valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setTipo(TipoTransacao.DEPOSITO);
        transacao.setValor(valor);
        transacao.setDataHora(LocalDateTime.now());
        transacao.setContaOrigem(conta);
        transacaoRepository.save(transacao);

        return conta;
    }

    @Transactional
    public Conta sacar(Long contaId, BigDecimal valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (!conta.podeSacar(valor)) {
            throw new RuntimeException("Saldo insuficiente para realizar o saque");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setTipo(TipoTransacao.SAQUE);
        transacao.setValor(valor);
        transacao.setDataHora(LocalDateTime.now());
        transacao.setContaOrigem(conta);
        transacaoRepository.save(transacao);

        return conta;
    }

    public List<Transacao> buscarExtrato(Long contaId) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        return transacaoRepository.findByContaOrigem(conta);
    }
}