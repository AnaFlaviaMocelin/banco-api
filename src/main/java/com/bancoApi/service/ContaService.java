package com.bancoApi.service;

import com.bancoApi.dto.LancamentoDTO;
import com.bancoApi.dto.SaldoDTO;
import com.bancoApi.model.Conta;
import com.bancoApi.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

  private final ContaRepository contaRepository;

  public ContaService(ContaRepository contaRepository) {
    this.contaRepository = contaRepository;
  }

  public Conta criarConta(String numeroConta, BigDecimal saldoInicial) {
    validarContaJaExiste(numeroConta);
    Conta conta = new Conta(numeroConta, saldoInicial);
    return contaRepository.save(conta);
  }

  private void validarContaJaExiste(String numeroConta) {
    Optional<Conta> contaOpt = contaRepository.findByNumeroConta(numeroConta);
    if (contaOpt.isPresent()) {
      throw new com.bancoApi.exception.ContaJaExisteException ("Conta com número " + numeroConta + " já existe.");
    }
  }

  public Conta realizarLancamentos(List<LancamentoDTO> lancamentos) {
    Conta updatedConta = null;
    for (LancamentoDTO lancamento : lancamentos) {
      Conta conta = contaRepository.findById(lancamento.getContaId())
      .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

      BigDecimal novoSaldo = conta.getSaldo();
      if ("CREDITO".equalsIgnoreCase(lancamento.getTipo())) {
        novoSaldo = novoSaldo.add(lancamento.getValor());
      } else if ("DEBITO".equalsIgnoreCase(lancamento.getTipo())) {
        novoSaldo = novoSaldo.subtract(lancamento.getValor());
      }

      conta.setSaldo(novoSaldo);
      updatedConta = contaRepository.save(conta);
    }
    return updatedConta;
  }

  public SaldoDTO consultarSaldoPorId(Long contaId) {
    Optional<Conta> contaOpt = contaRepository.findById(contaId);
    return contaOpt.map(conta -> new SaldoDTO(conta.getSaldo())).orElse(null);
  }
}