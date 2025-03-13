package com.bancoApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CriarContaDTO {

  @NotBlank(message = "Número da conta é obrigatório")
  private String numeroConta;

  @NotNull(message = "Saldo inicial é obrigatório")
  @Positive(message = "Saldo inicial deve ser maior que zero")
  private BigDecimal saldoInicial;

  public CriarContaDTO() {
  }

  public CriarContaDTO(String numeroConta, BigDecimal saldoInicial) {
    this.numeroConta = numeroConta;
    this.saldoInicial = saldoInicial;
  }
}