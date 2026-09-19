package com.pactomais.bancoapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Número único de identificação da conta
    @Column(nullable = false, unique = true, length = 10)
    private String numeroConta;

    @Column(nullable = false, length = 10)
    private String agencia;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    // Relacionamento: várias contas podem pertencer ao mesmo correntista (Chave Estrangeira)
    @ManyToOne
    @JoinColumn(name = "correntista_id", nullable = false)
    private Correntista correntista;

    public Conta() {
    }

    public Conta(String numeroConta, String agencia, BigDecimal saldo, Correntista correntista) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.correntista = correntista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }
}