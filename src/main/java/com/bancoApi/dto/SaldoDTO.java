package com.bancoApi.dto;

import java.math.BigDecimal;

@lombok.Getter
public class SaldoDTO {

  private BigDecimal saldo;

  public SaldoDTO(BigDecimal saldo) {
    this.saldo = saldo;
  }

  public void setSaldo(BigDecimal saldo) {
    this.saldo = saldo;
  }
}