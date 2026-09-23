package com.pactomais.bancoapi.controller;

import com.pactomais.bancoapi.dto.CorrentistaDTO;
import com.pactomais.bancoapi.model.Correntista;
import com.pactomais.bancoapi.service.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;

    @PostMapping
    public ResponseEntity<Correntista> cadastrar(@RequestBody CorrentistaDTO dto) {
        Correntista correntista = new Correntista();
        correntista.setNome(dto.getNome());
        correntista.setDocumento(dto.getDocumento());
        correntista.setEmail(dto.getEmail());
        correntista.setTelefone(dto.getTelefone());
        Correntista salvo = correntistaService.salvar(correntista);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Correntista>> listar() {
        return ResponseEntity.ok(correntistaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Correntista> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(correntistaService.buscarPorId(id));
    }
}