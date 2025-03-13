package com.bancoApi.model;

import com.bancoApi.Enums.TipoLancamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@lombok.Getter
@Entity
@Table(name = "lancamento")
public class Lancamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "conta_id", nullable = false)
  private Conta conta;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoLancamento tipo;

  @Column(nullable = false)
  private BigDecimal valor;

  @Column(nullable = false)
  private LocalDateTime dataHora;

  public Lancamento() {
    this.dataHora = LocalDateTime.now();
  }

  public Lancamento(Conta conta, TipoLancamento tipo, BigDecimal valor) {
    this.conta = conta;
    this.tipo = tipo;
    this.valor = valor;
    this.dataHora = LocalDateTime.now();
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setConta(Conta conta) {
    this.conta = conta;
  }

  public void setTipo(TipoLancamento tipo) {
    this.tipo = tipo;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
  }
}