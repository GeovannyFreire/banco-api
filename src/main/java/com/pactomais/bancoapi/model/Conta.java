package com.pactomais.bancoapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroConta;

    @Column(nullable = false)
    private String agencia;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "correntista_id", nullable = false)
    private Correntista correntista;

    public abstract boolean podeSacar(BigDecimal valor);

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroConta() { return numeroConta; }
    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public Correntista getCorrentista() { return correntista; }
    public void setCorrentista(Correntista correntista) { this.correntista = correntista; }
}