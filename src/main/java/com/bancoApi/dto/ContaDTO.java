package com.bancoApi.dto;

import java.math.BigDecimal;

public class ContaDTO {


    private Long id;

    private BigDecimal saldo;

    public ContaDTO() {
    }

    public ContaDTO(Long id, BigDecimal saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}