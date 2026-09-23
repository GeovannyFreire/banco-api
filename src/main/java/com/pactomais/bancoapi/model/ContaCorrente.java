package com.pactomais.bancoapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta {

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal limite = BigDecimal.ZERO;

    @Override
    public boolean podeSacar(BigDecimal valor) {
        return getSaldo().add(limite).compareTo(valor) >= 0;
    }

    public BigDecimal getLimite() { return limite; }
    public void setLimite(BigDecimal limite) { this.limite = limite; }
}