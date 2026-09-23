package com.pactomais.bancoapi.controller;

import com.pactomais.bancoapi.dto.AberturaContaDTO;
import com.pactomais.bancoapi.dto.OperacaoDTO;
import com.pactomais.bancoapi.model.Conta;
import com.pactomais.bancoapi.model.ContaCorrente;
import com.pactomais.bancoapi.model.ContaPoupanca;
import com.pactomais.bancoapi.model.Transacao;
import com.pactomais.bancoapi.service.ContaAberturaService;
import com.pactomais.bancoapi.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaAberturaService contaAberturaService;

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Conta> abrir(@RequestBody AberturaContaDTO dto) {
        Conta novaConta;
        if ("CORRENTE".equalsIgnoreCase(dto.getTipo())) {
            ContaCorrente cc = new ContaCorrente();
            if (dto.getLimite() != null) {
                cc.setLimite(dto.getLimite());
            }
            novaConta = cc;
        } else {
            novaConta = new ContaPoupanca();
        }
        Conta salva = contaAberturaService.abrir(novaConta, dto.getCorrentistaId());
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        return ResponseEntity.ok(contaAberturaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contaAberturaService.buscarPorId(id));
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestBody OperacaoDTO dto) {
        return ResponseEntity.ok(contaService.depositar(id, dto.getValor()));
    }

    @PostMapping("/{id}/sacar")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestBody OperacaoDTO dto) {
        return ResponseEntity.ok(contaService.sacar(id, dto.getValor()));
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<List<Transacao>> extrato(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.buscarExtrato(id));
    }
}