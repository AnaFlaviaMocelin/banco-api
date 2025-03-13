package com.bancoApi.dto;

import lombok.Getter;

import java.math.BigDecimal;


@lombok.Setter
@Getter
public class LancamentoDTO {
 @jakarta.validation.constraints.NotNull(message = "Id da conta é obrigatório")
 @jakarta.validation.constraints.Positive(message = "Id da conta deve ser maior que zero")
  private Long contaId;

 @jakarta.validation.constraints.NotNull(message = "Tipo do lançamento é obrigatório")
  private String tipo;

  @jakarta.validation.constraints.NotNull(message = "Valor do lançamento é obrigatório")
  private BigDecimal valor;

  public LancamentoDTO () {
  }

  public LancamentoDTO (Long contaId, String tipo, BigDecimal valor) {
    this.contaId = contaId;
    this.tipo = tipo;
    this.valor = valor;
  }

  public Long getContaId () {
    return contaId;
  }

}
