package com.bancoApi.controller;

import com.bancoApi.dto.CriarContaDTO;
import com.bancoApi.dto.LancamentoDTO;
import com.bancoApi.dto.SaldoDTO;
import com.bancoApi.model.Conta;
import com.bancoApi.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
@Validated
public class ContaController {

  private final ContaService contaService;

  @PostMapping
  public ResponseEntity<Conta> criarConta(@RequestBody @Valid CriarContaDTO criarContaDTO) {
    Conta novaConta = contaService.criarConta(criarContaDTO.getNumeroConta(), criarContaDTO.getSaldoInicial());
    return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
  }

  @PostMapping("/lancamentos")
  public ResponseEntity<Conta> realizarLancamento(@RequestBody @Valid List<LancamentoDTO> lancamentos) {
    Conta contaAtualizada = contaService.realizarLancamentos(lancamentos);
    contaAtualizada.setId(contaAtualizada.getId());
    return ResponseEntity.ok(contaAtualizada);
  }

  @GetMapping("/id/{contaId}/saldo")
  public ResponseEntity<SaldoDTO> consultarSaldoPorId(@PathVariable Long contaId) {
    return ResponseEntity.ok(contaService.consultarSaldoPorId(contaId));
  }
}