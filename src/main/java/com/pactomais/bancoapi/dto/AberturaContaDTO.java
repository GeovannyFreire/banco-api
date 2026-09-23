package com.pactomais.bancoapi.dto;

import java.math.BigDecimal;

public class AberturaContaDTO {
    private Long correntistaId;
    private String tipo;
    private BigDecimal limite;

    public Long getCorrentistaId() { return correntistaId; }
    public void setCorrentistaId(Long correntistaId) { this.correntistaId = correntistaId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getLimite() { return limite; }
    public void setLimite(BigDecimal limite) { this.limite = limite; }
}