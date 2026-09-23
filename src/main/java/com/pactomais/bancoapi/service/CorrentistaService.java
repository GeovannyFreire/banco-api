package com.pactomais.bancoapi.service;

import com.pactomais.bancoapi.model.Correntista;
import com.pactomais.bancoapi.repository.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public Correntista salvar(Correntista correntista) {
        return correntistaRepository.save(correntista);
    }

    public List<Correntista> listarTodos() {
        return correntistaRepository.findAll();
    }

    public Correntista buscarPorId(Long id) {
        return correntistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Correntista não encontrado"));
    }
}