package com.pactomais.bancoapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends Conta {

    @Override
    public boolean podeSacar(BigDecimal valor) {
        return getSaldo().compareTo(valor) >= 0;
    }
}